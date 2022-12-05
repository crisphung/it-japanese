package quan.hust.itjapanese.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDto
{
  @JsonProperty("id")
  private Integer Id;

  @JsonProperty("content")
  private String content;

  @JsonProperty("created-at")
  private Timestamp createdAt;

  @JsonProperty("created-by")
  private String createdBy;

}
