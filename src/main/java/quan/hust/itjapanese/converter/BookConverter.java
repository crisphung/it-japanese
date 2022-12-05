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
      .ranking(entity.getRanking())
      .category(entity.getCategory())
      .price(entity.getPrice())
      .id(entity.getId())
      .name(entity.getName())
      .level(entity.getLevel())
      .author(entity.getAuthor())
      .build();
  }
}
