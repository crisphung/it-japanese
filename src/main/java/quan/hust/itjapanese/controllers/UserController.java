package quan.hust.itjapanese.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import quan.hust.itjapanese.dto.request.LoginRequest;
import quan.hust.itjapanese.dto.request.SignUpRequest;
import quan.hust.itjapanese.dto.response.AuthResponse;
import quan.hust.itjapanese.services.UserService;

@RestController
public class UserController implements UserOperations
{

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
}
