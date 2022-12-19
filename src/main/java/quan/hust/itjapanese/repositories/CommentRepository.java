package quan.hust.itjapanese.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import quan.hust.itjapanese.domain.Book;
import quan.hust.itjapanese.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>
{
  List<Comment> findCommentByBookOrderByCreatedAtDesc(Book book);
}
