package quan.hust.itjapanese.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import quan.hust.itjapanese.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
  Optional<User> findUserInfoByUsername(String username);
}