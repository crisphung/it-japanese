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
import quan.hust.itjapanese.dto.request.LoginRequest;
import quan.hust.itjapanese.dto.request.SignUpRequest;
import quan.hust.itjapanese.dto.response.AuthResponse;
import quan.hust.itjapanese.dto.response.FavoriteResponse;
import quan.hust.itjapanese.dto.response.ProfileResponse;

@RequestMapping(UserOperations.SOURCE_API)
public interface UserOperations
{
  String SOURCE_API = "/api/user";

  String LOGIN = "/login";

  String LOGOUT = "/logout";
  String SIGNUP = "/signup";

  String FAVORITE_BOOK = "/favorite";

  @PostMapping(LOGIN)
  ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request);

  @PostMapping(SIGNUP)
  ResponseEntity<AuthResponse> signup(@RequestBody SignUpRequest request);

  @PostMapping("/update")
  UserDto update(@RequestParam MultipartFile imageFile) throws IOException;

  @GetMapping(value = "/update-profile",produces = MediaType.IMAGE_PNG_VALUE)
  void updateProfile(@RequestParam(name = "imagePath",required = false) String imagePath, HttpServletResponse response);

  @GetMapping(FAVORITE_BOOK)
  ResponseEntity<FavoriteResponse> addToFavorite(@RequestParam(name = "bookId") Integer bookId);

  @GetMapping(value = "/profile")
  ResponseEntity<ProfileResponse> getProfile();
}
