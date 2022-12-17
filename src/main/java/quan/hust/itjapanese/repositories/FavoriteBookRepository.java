package quan.hust.itjapanese.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import quan.hust.itjapanese.domain.FavoriteBook;
import quan.hust.itjapanese.domain.UserBookID;

@Repository
public interface FavoriteBookRepository extends JpaRepository<FavoriteBook, UserBookID>
{
  List<FavoriteBook> findFavoriteBookByCreatedBy(String username);
}
