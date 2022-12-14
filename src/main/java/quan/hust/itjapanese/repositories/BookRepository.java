package quan.hust.itjapanese.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import quan.hust.itjapanese.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, BookRepositoryCustom
{
  @Override
  Optional<Book> findById(Integer integer);
}
