package quan.hust.itjapanese.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.HashCodeExclude;

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

    public static  final String STAR = "star";
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer Id;

  @Column(name = "name")
  private String name;

  @Column(name = ColumnName.PRICE)
  private Double price;

  @Column(name = "description")
  private String description;

  @Column(name="level")
  private String level;
    
  @Column(name = "author")
  private String author;

  @Column(name = ColumnName.STAR)
  private int star;

  @Column(name = "time_to_learn")
  private int timeLearn;

  @Column(name="category")
  private String category;

  @Column(name="image_url")
  private String imageUrl;

  @Column(name = "rate_times")
  private int rateTimes;

}
