package quan.hust.itjapanese.utils;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import quan.hust.itjapanese.security.CustomUserDetails;

public final class SecurityUtils
{

  private SecurityUtils()
  {
  }

  /**
   * Get username of current logged user.
   *
   * @return current username if user has logged. Otherwise return {@link Optional#empty()}
   */
  public static Optional<String> getCurrentUsername()
  {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || authentication.getPrincipal() == null)
    {
      return Optional.empty();
    }

    // Resolve username depend on type of principal.
    Object principal = authentication.getPrincipal();
    if (principal instanceof String)
    {
      return Optional.of(principal.toString());
    }
    else if (principal instanceof CustomUserDetails)
    {
      CustomUserDetails userDetails = (CustomUserDetails)principal;
      return Optional.of(userDetails.getUsername());
    }
    else
    {
      return Optional.empty();
    }
  }


}
