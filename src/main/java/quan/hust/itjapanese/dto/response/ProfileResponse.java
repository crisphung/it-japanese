package quan.hust.itjapanese.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import quan.hust.itjapanese.dto.BookDto;
import quan.hust.itjapanese.dto.UserDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileResponse
{
  @JsonProperty("user_info")
  private UserDto userInfo;

  @JsonProperty("favorite_books")
  private List<BookDto> favoriteBook;

  @JsonProperty("message")
  private String message;
}
