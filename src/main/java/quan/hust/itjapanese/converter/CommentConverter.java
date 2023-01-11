package quan.hust.itjapanese.converter;

import org.springframework.stereotype.Component;

import quan.hust.itjapanese.domain.Comment;
import quan.hust.itjapanese.dto.CommentDto;

@Component
public class CommentConverter implements EntityConverter<Comment, CommentDto>
{
  @Override
  public CommentDto convertToDto(Comment entity)
  {
    return CommentDto.builder()
      .content(entity.getContent())
      .createdAt(entity.getCreatedAt())
      .createdBy(entity.getCreatedBy())
      .start(entity.getStar())
      .like(entity.getLike())
      .dislike(entity.getDislike())
      .Id(entity.getId())
      .build();
  }
}
