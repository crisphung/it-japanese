package quan.hust.itjapanese.controllers;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import quan.hust.itjapanese.dto.UserDto;
import quan.hust.itjapanese.dto.request.AddFavoriteRequest;
import quan.hust.itjapanese.dto.request.LoginRequest;
import quan.hust.itjapanese.dto.request.SignUpRequest;
import quan.hust.itjapanese.dto.request.UpdateProfileRequest;
import quan.hust.itjapanese.dto.request.UserActivityRequest;
import quan.hust.itjapanese.dto.response.AuthResponse;
import quan.hust.itjapanese.dto.response.FavoriteResponse;
import quan.hust.itjapanese.dto.response.ManipulateCommentResponse;
import quan.hust.itjapanese.dto.response.ProfileResponse;
import quan.hust.itjapanese.services.UserActivityService;
import quan.hust.itjapanese.services.UserService;

@RestController
public class UserController implements UserOperations
{
  public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads/";
  @Autowired
  private UserService userService;

  @Autowired
  private UserActivityService activityService;

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
  public ResponseEntity<FavoriteResponse> addToFavorite(AddFavoriteRequest request)
  {
    return ResponseEntity.ok(userService.addToFavorite(request.getBookId()));
  }

  @Override
  public ResponseEntity<ProfileResponse> getProfile()
  {
    return ResponseEntity.ok(userService.getProfile());
  }

  public ResponseEntity<ManipulateCommentResponse> reactComment(UserActivityRequest request)
  {
    ManipulateCommentResponse response = activityService.addActivity(request);

    return ResponseEntity.ok(
      ManipulateCommentResponse.builder()
        .message("Success").build()
    );
  }

  @Override
  public ResponseEntity<String> likedComment(Integer cmtId)
  {
    return ResponseEntity.ok(activityService.checkHadActivity(cmtId));
  }

  @Override
  public ResponseEntity<ProfileResponse> updateProfile(UpdateProfileRequest request)
  {
    ProfileResponse response = userService.updateProfile(request);
    return ResponseEntity.ok(response);
  }
}
