package quan.hust.itjapanese.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest
{
  @JsonProperty("email")
  private String email;

  @NonNull
  @JsonProperty("name")
  private String name;

  @NonNull
  @JsonProperty("username")
  private String username;

  @NonNull
  @JsonProperty("password")
  private String password;

}
