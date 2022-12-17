package quan.hust.itjapanese.controllers;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import quan.hust.itjapanese.dto.UserDto;
import quan.hust.itjapanese.dto.request.LoginRequest;
import quan.hust.itjapanese.dto.request.SignUpRequest;
import quan.hust.itjapanese.dto.response.AuthResponse;
import quan.hust.itjapanese.dto.response.FavoriteResponse;
import quan.hust.itjapanese.dto.response.ProfileResponse;
import quan.hust.itjapanese.services.UserService;

@RestController
public class UserController implements UserOperations
{
  public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads/";
  @Autowired
  private UserService userService;


  @Override
  public ResponseEntity<AuthResponse> login(LoginRequest request)
  {

    return ResponseEntity.ok(userService.login(request));
  }

  @Override
  public ResponseEntity<AuthResponse> signup(SignUpRequest request)
  {
    return ResponseEntity.ok(userService.signup(request));
  }

  @Override
  public UserDto update(MultipartFile imageFile) throws IOException
  {

    return userService.updateInfo(imageFile);
  }

  @Override
  public void updateProfile(String imagePath, HttpServletResponse response)
  {
    String imageFile = String.format("%s%s",UPLOAD_DIRECTORY,imagePath);
    File file = new File(imageFile);
    InputStream is = null;
    try
    {
      is = new FileInputStream(file);
      StreamUtils.copy(is,response.getOutputStream());
    }
    catch (IOException exception)
    {
      throw new RuntimeException(exception);
    }

  }

  @Override
  public ResponseEntity<FavoriteResponse> addToFavorite(Integer bookId)
  {
    return ResponseEntity.ok(userService.addToFavorite(bookId));
  }

  @Override
  public ResponseEntity<ProfileResponse> getProfile()
  {
    return ResponseEntity.ok(userService.getProfile());
  }
}
