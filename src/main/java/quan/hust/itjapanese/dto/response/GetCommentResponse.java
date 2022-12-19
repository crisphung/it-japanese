package quan.hust.itjapanese.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import quan.hust.itjapanese.dto.CommentDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetCommentResponse
{
  @JsonProperty("comments")
  private List<CommentDto> comments;

  @JsonProperty("message")
  private String message;

}
