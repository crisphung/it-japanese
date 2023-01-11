package quan.hust.itjapanese.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileRequest
{
  @JsonProperty("phone")
  private String phone;

  @JsonProperty("image_url")
  private String imageUrl;

  @JsonProperty("email")
  private String email;
}
