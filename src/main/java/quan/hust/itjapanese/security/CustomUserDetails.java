package quan.hust.itjapanese.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import quan.hust.itjapanese.domain.User;

@Getter
@Setter
@AllArgsConstructor
public class CustomUserDetails implements UserDetails
{
  private static final long serialVersionUID = 8927583625600001881L;
  User userInfo;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities()
  {
    return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getPassword()
  {
    return userInfo.getPassword();
  }

  @Override
  public String getUsername()
  {
    return userInfo.getUsername();
  }

  @Override
  public boolean isAccountNonExpired()
  {
    return true;
  }

  @Override
  public boolean isAccountNonLocked()
  {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired()
  {
    return true;
  }

  @Override
  public boolean isEnabled()
  {
    return true;
  }
}
