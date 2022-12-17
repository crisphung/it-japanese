package quan.hust.itjapanese.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book_rating")
public class BookRating extends InitializationInfo
{
  @EmbeddedId
  private UserBookID id;

  @Column(name="star")
  private Integer star;
}
