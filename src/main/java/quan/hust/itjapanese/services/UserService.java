package quan.hust.itjapanese.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import quan.hust.itjapanese.dto.UserDto;
import quan.hust.itjapanese.dto.request.LoginRequest;
import quan.hust.itjapanese.dto.request.SignUpRequest;
import quan.hust.itjapanese.dto.request.UpdateProfileRequest;
import quan.hust.itjapanese.dto.response.AuthResponse;
import quan.hust.itjapanese.dto.response.FavoriteResponse;
import quan.hust.itjapanese.dto.response.ProfileResponse;

public interface UserService extends UserDetailsService
{
  AuthResponse login(LoginRequest request);

  AuthResponse signup(SignUpRequest request);

  UserDto updateInfo(MultipartFile imageFile);

  FavoriteResponse addToFavorite(Integer bookId);

  ProfileResponse getProfile();

  ProfileResponse updateProfile(UpdateProfileRequest request);
}
