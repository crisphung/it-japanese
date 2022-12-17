package quan.hust.itjapanese.domain;

import java.io.Serializable;

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
@Table(name = "favorite_book")
public class FavoriteBook extends InitializationInfo implements Serializable
{
  @EmbeddedId
  private UserBookID id;
}
