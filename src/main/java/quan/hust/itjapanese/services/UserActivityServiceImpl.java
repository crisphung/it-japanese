package quan.hust.itjapanese.services;

import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quan.hust.itjapanese.converter.UserActivityConverter;
import quan.hust.itjapanese.domain.Comment;
import quan.hust.itjapanese.domain.User;
import quan.hust.itjapanese.domain.UserActivity;
import quan.hust.itjapanese.dto.ActivityDto;
import quan.hust.itjapanese.dto.request.UserActivityRequest;
import quan.hust.itjapanese.dto.response.ManipulateCommentResponse;
import quan.hust.itjapanese.repositories.CommentRepository;
import quan.hust.itjapanese.repositories.UserActivityRepository;
import quan.hust.itjapanese.type.ActivityType;
import quan.hust.itjapanese.utils.SecurityUtils;

@Service
public class UserActivityServiceImpl implements UserActivityService
{
  @Autowired
  private UserActivityRepository userActivityRepository;

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private UserActivityConverter activityConverter;

  @Override
  public ManipulateCommentResponse addActivity(UserActivityRequest request)
  {
    String type = request.getType().toUpperCase(Locale.ROOT);
    Integer cmtId = request.getId();
    Optional<Comment> commentOpt = commentRepository.findById(cmtId);
    if (commentOpt.isPresent())
    {
      Comment comment = commentOpt.get();
      Integer currLike = comment.getLike() != null ? comment.getLike() : 0;
      Integer curDislike = comment.getDislike() != null ? comment.getDislike() : 0;
      if (ActivityType.LIKE.getValue().equals(type))
      {
        comment.setLike(currLike + 1);
      }
      if (ActivityType.DISLIKE.getValue().equals(type))
      {
        comment.setDislike(curDislike + 1);
      }
      if (ActivityType.EDIT.getValue().equals(type))
      {
        comment.setContent(request.getContent());
      }
      if (ActivityType.UNLIKE.getValue().equals(type))
      {
        if (currLike <= 0)
        {
          comment.setLike(0);
        }
        else
        {
          comment.setLike(currLike - 1);
        }

      }
      if (ActivityType.UNDISLIKE.getValue().equals(type))
      {
        if (curDislike <= 0)
        {
          comment.setDislike(0);
        }
        else
        {
          comment.setDislike(curDislike - 1);
        }

      }

      comment = commentRepository.save(comment);

      updateUserActivity(comment, request);

      return ManipulateCommentResponse.builder().message("Add success").build();
    }

    return ManipulateCommentResponse.builder().error("Add activity failed!").build();

  }

  private void updateUserActivity(Comment comment, UserActivityRequest request)
  {
    Optional<User> userOpt = SecurityUtils.getCurrentUser();
    if (userOpt.isPresent())
    {

      String userName = userOpt.get().getUsername();
      Optional<UserActivity> activityOpt = userActivityRepository.findUserActivityByCreatedByAndComment(userName,
        comment);
      if (activityOpt.isPresent())
      {
        UserActivity activity = activityOpt.get();
        activity.setType(request.getType());
        activity.setContent(activity.getContent());
        activity.setComment(comment);

        activity = userActivityRepository.save(activity);
      }
      else
      {
        UserActivity activity = UserActivity.builder()
          .content(request.getContent())
          .type(request.getType())
          .comment(comment)
          .build();

        activity = userActivityRepository.save(activity);
      }

    }
  }

  @Override
  public String checkHadActivity(Integer cmdId)
  {
    Comment comment = commentRepository.findById(cmdId).orElse(null);
    Optional<User> userOpt = SecurityUtils.getCurrentUser();
    if (userOpt.isPresent())
    {
      User user = userOpt.get();
      Optional<UserActivity> activity = userActivityRepository.findUserActivityByCreatedByAndComment(user.getUsername(),
        comment);

      if (activity.isPresent())
      {
        return activity.get().getType();
      }
    }
    return null;
  }
}
