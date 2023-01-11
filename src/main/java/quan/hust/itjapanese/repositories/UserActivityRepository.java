package quan.hust.itjapanese.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import quan.hust.itjapanese.domain.Comment;
import quan.hust.itjapanese.domain.UserActivity;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Integer>
{
  Optional<UserActivity> findUserActivityByCreatedByAndComment(String createdBy, Comment comment);
}
