package quan.hust.itjapanese.services;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.Opt;
import quan.hust.itjapanese.config.jwt.JwtTokenProvider;
import quan.hust.itjapanese.converter.BookConverter;
import quan.hust.itjapanese.converter.UserConverter;
import quan.hust.itjapanese.domain.Book;
import quan.hust.itjapanese.domain.FavoriteBook;
import quan.hust.itjapanese.domain.UserBookID;
import quan.hust.itjapanese.domain.User;
import quan.hust.itjapanese.dto.BookDto;
import quan.hust.itjapanese.dto.UserDto;
import quan.hust.itjapanese.dto.request.LoginRequest;
import quan.hust.itjapanese.dto.request.SignUpRequest;
import quan.hust.itjapanese.dto.request.UpdateProfileRequest;
import quan.hust.itjapanese.dto.response.AuthResponse;
import quan.hust.itjapanese.dto.response.FavoriteResponse;
import quan.hust.itjapanese.dto.response.ProfileResponse;
import quan.hust.itjapanese.repositories.BookRepository;
import quan.hust.itjapanese.repositories.FavoriteBookRepository;
import quan.hust.itjapanese.repositories.UserRepository;
import quan.hust.itjapanese.security.CustomUserDetails;
import quan.hust.itjapanese.utils.FileUploadUtils;
import quan.hust.itjapanese.utils.SecurityUtils;

@Service
@Slf4j
public class UserServiceImpl implements UserService
{
  public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads/";

  @Value("${jwt.expirationDateInMs}")
  private Long expiresToken;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserConverter userConverter;

  @Autowired
  private BookConverter bookConverter;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private FavoriteBookRepository fbRepository;

  @Override
  @Transactional
  public AuthResponse login(LoginRequest request)
  {
    AuthResponse response;

    try
    {
      Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
      );
      SecurityContextHolder.getContext().setAuthentication(authentication);
      CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();

      String token = jwtTokenProvider.generateToken(userDetails);
      response = AuthResponse.builder()
        .accessToken(token)
        .expiresIn(expiresToken)
        .name(userDetails.getUsername())
        .build();
    }
    catch (AuthenticationException exception)
    {
      log.error(exception.getMessage());
      response = AuthResponse.builder()
        .error("Username or password is incorrect!")
        .build();
    }

    return response;
  }

  @Override
  @Transactional
  public AuthResponse signup(SignUpRequest request)
  {
    AuthResponse response = null;
    String username = request.getUsername();
    boolean checkUserExist = checkUserExisted(username);
    User userInfo = User
      .builder()
      .email(request.getEmail())
      .name(request.getName())
      .username(request.getUsername())
      .password(passwordEncoder.encode(request.getPassword()))
      .build();

    if (!checkUserExist)
    {
      userRepository.save(userInfo);
    }
    else
    {
      response = AuthResponse.builder().error("User existed!").build();
    }

    return response;

  }

  @Override
  public UserDto updateInfo(MultipartFile imageFile)
  {
    UserDto userDto = null;
    Path filePath = null;
    try
    {
      String uploadDir = FileUploadUtils.extractUploadDirectory();
      filePath = FileUploadUtils.saveFile(UPLOAD_DIRECTORY + uploadDir, imageFile.getOriginalFilename(), imageFile);

      Optional<User> userOpt = SecurityUtils.getCurrentUser();
      if (userOpt.isPresent())
      {
        User user = userOpt.get();

        String imagePath = String.format("%s/%s",uploadDir,filePath.getFileName());
        user.setProfileImage(imagePath);
        user = userRepository.save(user);

        userDto = userConverter.convertToDto(user);
      }
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
    return userDto;
  }

  @Override
  @Transactional
  public FavoriteResponse addToFavorite(Integer bookId)
  {
    Optional<User> userOpt = SecurityUtils.getCurrentUser();
    Optional<Book> bookOpt = bookRepository.findById(bookId);

    if(!userOpt.isPresent())
    {
      return FavoriteResponse.builder()
        .message("User not available!")
        .build();
    }

    if(!bookOpt.isPresent())
    {
      return FavoriteResponse.builder()
        .message("Book not found!")
        .build();
    }
    User user = userOpt.get();
    Book book = bookOpt.get();

    UserBookID fbID = UserBookID.builder()
      .userId(user.getId())
      .bookId(bookId)
      .build();

    FavoriteBook fb = FavoriteBook.builder()
      .id(fbID).build();

    fb =fbRepository.save(fb);

    return FavoriteResponse.builder()
    .message("Successfully add to favorite! ")
    .build();
  }

  @Override
  @Transactional
  public ProfileResponse getProfile()
  {
    ProfileResponse response = null;
    Optional<User> userOpt = SecurityUtils.getCurrentUser();
    if(userOpt.isPresent())
    {
      User user = userOpt.get();
      UserDto userDto = userConverter.convertToDto(user);
      List<FavoriteBook> favoriteBooks = fbRepository.findFavoriteBookByCreatedBy(user.getUsername());

      List<Integer> bookIds = favoriteBooks.stream()
                                          .map(fb -> fb.getId().getBookId())
                                          .collect(Collectors.toList());
      List<Book> books = bookRepository.findAllById(bookIds);
      List<BookDto> bookDtos = bookConverter.convertToDtoList(books);

      response = ProfileResponse.builder()
        .favoriteBook(bookDtos)
        .userInfo(userDto)
        .message("Get user info!")
        .build();
    }else
    {
      response = ProfileResponse.builder()
        .message("Get profile failed!")
        .build();
    }
    return response;
  }

  @Override
  @Transactional
  public ProfileResponse updateProfile(UpdateProfileRequest request)
  {
    ProfileResponse response = new ProfileResponse();
    String image = request.getImageUrl();
    String phone = request.getPhone();
    Optional<User> currUser = SecurityUtils.getCurrentUser();
    if(currUser.isPresent())
    {
        User user = currUser.get();
        if(StringUtils.hasText(image))
        {
          user.setProfileImage(image);
        }
        if(StringUtils.hasText(phone))
        {
          user.setPhone(phone);
        }

        user = userRepository.save(user);

        response.setUserInfo(userConverter.convertToDto(user));
        response.setMessage("Update Success");
        return response;
    }
    response.setMessage("Update failed!");
    return response;
  }

  private boolean checkUserExisted(String username) {
    Optional<User> userInfo = userRepository.findUserInfoByUsername(username);
    return userInfo.isPresent();
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
  {
    Optional<User> userInfo = userRepository.findUserInfoByUsername(username);
    if (userInfo.isPresent()) {
      return new CustomUserDetails(userInfo.get());

    } else {
      throw new UsernameNotFoundException("Username not available!");
    }

  }
}
