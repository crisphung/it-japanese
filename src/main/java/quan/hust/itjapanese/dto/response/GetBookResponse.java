package quan.hust.itjapanese.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import quan.hust.itjapanese.dto.BookDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetBookResponse
{
  @JsonProperty("books")
  private List<BookDto> books;

  @JsonProperty("count")
  private long total;
}
