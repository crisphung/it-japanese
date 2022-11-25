package quan.hust.itjapanese.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import quan.hust.itjapanese.dto.request.LoginRequest;
import quan.hust.itjapanese.dto.request.SignUpRequest;
import quan.hust.itjapanese.dto.response.AuthResponse;

public interface UserService extends UserDetailsService
{
  AuthResponse login(LoginRequest request);

  AuthResponse signup(SignUpRequest request);

}
