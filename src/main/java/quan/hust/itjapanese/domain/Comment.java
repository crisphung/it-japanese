package quan.hust.itjapanese.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
public class Comment extends InitializationInfo
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer Id;

  @Column(name = "content")
  private String content;

  @Column(name="star")
  private Integer star;

  @Column(name="likes")
  private Integer like;

  @Column(name="dislike")
  private Integer dislike;
  @ManyToOne
  @OnDelete(action = OnDeleteAction.NO_ACTION)
  @JoinColumn(name = "book_id",nullable = false)
  @ToString.Exclude
  private Book book;




}
