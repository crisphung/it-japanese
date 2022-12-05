package quan.hust.itjapanese.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookFilter
{
  private Integer id;

  private String text;

  private Double minPrice;

  private Double maxPrice;

  private String level;

  private Integer ranking;

  private String category;

  private String author;
}
