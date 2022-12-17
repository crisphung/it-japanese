package quan.hust.itjapanese.converter;

import org.springframework.stereotype.Component;

import quan.hust.itjapanese.domain.Book;
import quan.hust.itjapanese.dto.BookDto;

@Component
public class BookConverter implements EntityConverter<Book, BookDto>
{
  @Override
  public BookDto convertToDto(Book entity)
  {
    return BookDto.builder()
      .star(entity.getStar())
      .rateTimes(entity.getRateTimes())
      .category(entity.getCategory())
      .price(entity.getPrice())
      .id(entity.getId())
      .name(entity.getName())
      .level(entity.getLevel())
      .author(entity.getAuthor())
      .timeLearn(entity.getTimeLearn())
      .description(entity.getDescription())
      .imageUrl(entity.getImageUrl())
      .build();
  }
}
