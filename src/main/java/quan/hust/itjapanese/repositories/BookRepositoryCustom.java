package quan.hust.itjapanese.repositories;

import java.util.List;

import com.querydsl.core.types.OrderSpecifier;
import quan.hust.itjapanese.domain.Book;
import quan.hust.itjapanese.dto.filter.BookFilter;

public interface BookRepositoryCustom
{
  List<Book> getBooksByFilter(BookFilter filter, OrderSpecifier<String>[] orderSpecifier);

  long count(BookFilter filter);
}
