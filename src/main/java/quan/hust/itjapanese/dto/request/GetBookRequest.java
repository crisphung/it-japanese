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
public class GetBookRequest
{
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("name")
  private String text;

  @JsonProperty("minPrice")
  private Double minPrice;

  @JsonProperty("maxPrice")
  private Double maxPrice;

  @JsonProperty("level")
  private String level;

  @JsonProperty("ranking")
  private Integer ranking;

  @JsonProperty("category")
  private String category;

  @JsonProperty("author")
  private String author;

  @JsonProperty("orderBy")
  private String[] orderBy;
}
