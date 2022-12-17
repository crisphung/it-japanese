package quan.hust.itjapanese.dto;

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
public class BookDto
{
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("price")
  private Double price;

  @JsonProperty("description")
  private String description;

  @JsonProperty("level")
  private String level;

  @JsonProperty("star")
  private Integer star;

  @JsonProperty("rate_times")
  private Integer rateTimes;

  @JsonProperty("category")
  private String category;

  @JsonProperty("time_to_learn")
  private int timeLearn;

  @JsonProperty("author")
  private String author;

  @JsonProperty("image_url")
  private String imageUrl;
}
