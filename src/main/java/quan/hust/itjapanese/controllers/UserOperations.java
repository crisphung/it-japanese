package quan.hust.itjapanese.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

@RequestMapping(UserOperations.SOURCE_API)
public interface UserOperations
{
  String SOURCE_API = "/api/user";

  String LOGIN = "/login";

  String LOGOUT = "/logout";
  String SIGNUP = "/signup";

  String REACT ="/react-comment";

  String LIKED ="/liked-comment";
  String FAVORITE_BOOK = "/favorite";

  String UPDATE = "/update-profile";

  @PostMapping(LOGIN)
  ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request);

  @PostMapping(SIGNUP)
  ResponseEntity<AuthResponse> signup(@RequestBody SignUpRequest request);

  @PostMapping("/update")
  UserDto update(@RequestParam MultipartFile imageFile) throws IOException;

  @GetMapping(value = "/update-profile",produces = MediaType.IMAGE_PNG_VALUE)
  void updateProfile(@RequestParam(name = "imagePath",required = false) String imagePath, HttpServletResponse response);

  @PostMapping(FAVORITE_BOOK)
  ResponseEntity<FavoriteResponse> addToFavorite(@RequestBody AddFavoriteRequest request);

  @GetMapping(value = "/profile")
  ResponseEntity<ProfileResponse> getProfile();

  @PostMapping(value = REACT)
  ResponseEntity<ManipulateCommentResponse> reactComment(
    @RequestBody UserActivityRequest request
  );

  @GetMapping(value = LIKED)
  ResponseEntity<String> likedComment (
    @RequestParam(name = "cmtId") Integer cmtId
    );

  @PostMapping(UPDATE)
  ResponseEntity<ProfileResponse> updateProfile(@RequestBody UpdateProfileRequest request);

}
