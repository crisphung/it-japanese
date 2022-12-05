package quan.hust.itjapanese.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ch.qos.logback.classic.db.names.ColumnName;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class Book
{

  public static class ColumnName
  {
    private ColumnName(){}
    public static final String PRICE = "price";

    public static  final String RANKING = "ranking";
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer Id;

  @Column(name = "name")
  private String name;

  @Column(name = ColumnName.PRICE)
  private Double price;

  @Column(name="level")
  private String level;

  @Column(name = "author")
  private String author;

  @Column(name = ColumnName.RANKING)
  private Integer ranking;

  @Column(name="category")
  private String category;

  @ManyToMany
  @JoinTable(
    name = "favorite_book",
    joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name="user_id")
  )
  private Set<User> likes;

}
