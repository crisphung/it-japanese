package quan.hust.itjapanese.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import quan.hust.itjapanese.dto.BookDto;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompareBookResponse
{
  @JsonProperty("book_1")
  private BookDto firstBook;

  @JsonProperty("book_2")
  private BookDto secondBook;
}
