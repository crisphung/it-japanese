package quan.hust.itjapanese.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
public class UserActivity extends Auditable implements Serializable
{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer Id;

  @Column(name="type")
  private String type;

  @Column(name="content")
  private String content;

  @OneToOne(cascade = CascadeType.ALL)
  @OnDelete(action = OnDeleteAction.NO_ACTION)
  @JoinColumn(name = "commend_id", referencedColumnName = "id")
  private Comment comment;

}
