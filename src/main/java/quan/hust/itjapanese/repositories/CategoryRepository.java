package quan.hust.itjapanese.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import quan.hust.itjapanese.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>
{
}
