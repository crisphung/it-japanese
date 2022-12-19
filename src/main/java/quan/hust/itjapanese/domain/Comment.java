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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
public class Comment extends Auditable
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer Id;

  @Column(name = "content")
  private String content;

  @ManyToOne
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "book_id",nullable = false)
  private Book book;




}
