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
public class CommentRequest
{
  @JsonProperty("comment-id")
  private Integer commentId;

  @JsonProperty("book-id")
  private Integer bookId;

  @JsonProperty("content")
  private String content;
}
