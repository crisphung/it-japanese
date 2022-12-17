package quan.hust.itjapanese.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBookID implements Serializable
{
  @Column(name = "book_id")
  private Integer bookId;

  @Column(name = "user_id")
  private Integer userId;
}
