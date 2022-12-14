package quan.hust.itjapanese.controllers;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import quan.hust.itjapanese.dto.UserDto;
import quan.hust.itjapanese.dto.request.LoginRequest;
import quan.hust.itjapanese.dto.request.SignUpRequest;
import quan.hust.itjapanese.dto.response.AuthResponse;

@RequestMapping(UserOperations.SOURCE_API)
public interface UserOperations
{
  String SOURCE_API = "/api/user";

  String LOGIN = "/login";

  String LOGOUT = "/logout";
  String SIGNUP = "/signup";

  @PostMapping(LOGIN)
  ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request);

  @PostMapping(SIGNUP)
  ResponseEntity<AuthResponse> signup(@RequestBody SignUpRequest request);

  @PostMapping("/update")
  UserDto update(@RequestParam MultipartFile imageFile) throws IOException;
}
