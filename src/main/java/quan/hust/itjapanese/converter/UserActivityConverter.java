package quan.hust.itjapanese.converter;

import org.springframework.stereotype.Component;

import quan.hust.itjapanese.domain.Book;
import quan.hust.itjapanese.domain.UserActivity;
import quan.hust.itjapanese.dto.ActivityDto;
import quan.hust.itjapanese.dto.BookDto;
import quan.hust.itjapanese.services.UserActivityService;

@Component
public class UserActivityConverter implements EntityConverter<UserActivity, ActivityDto>
{

  @Override
  public ActivityDto convertToDto(UserActivity entity)
  {
    return ActivityDto.builder()
      .id(entity.getId())
      .comment(entity.getComment().getContent())
      .createdAt(entity.getCreatedAt())
      .build();
  }
}
