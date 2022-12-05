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
public class AddBookRequest
{
  @JsonProperty("name")
  private String name;

  @JsonProperty("price")
  private Double price;

  @JsonProperty("level")
  private String level;

  @JsonProperty("ranking")
  private Integer ranking;

  @JsonProperty("category")
  private String category;

  @JsonProperty("author")
  private String author;
}
