package quan.hust.itjapanese.services;

import java.util.Optional;

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

import lombok.extern.slf4j.Slf4j;
import quan.hust.itjapanese.config.jwt.JwtTokenProvider;
import quan.hust.itjapanese.domain.User;
import quan.hust.itjapanese.dto.request.LoginRequest;
import quan.hust.itjapanese.dto.request.SignUpRequest;
import quan.hust.itjapanese.dto.response.AuthResponse;
import quan.hust.itjapanese.repositories.UserRepository;
import quan.hust.itjapanese.security.CustomUserDetails;

@Service
@Slf4j
public class UserServiceImpl implements UserService
{
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


  @Override
  public AuthResponse login(LoginRequest request) {
    AuthResponse response;

    try
    {
      Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
      );
      SecurityContextHolder.getContext().setAuthentication(authentication);
      CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();


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
  public AuthResponse signup(SignUpRequest request) {
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

    if (!checkUserExist) {
      userRepository.save(userInfo);
    } else {
      response = AuthResponse.builder().error("User existed!").build();
    }

    return response;

  }

  private boolean checkUserExisted(String username) {
    Optional<User> userInfo = userRepository.findUserInfoByUsername(username);
    return userInfo.isPresent();
  }

  @Override
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
