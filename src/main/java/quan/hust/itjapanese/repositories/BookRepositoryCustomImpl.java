package quan.hust.itjapanese.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import quan.hust.itjapanese.domain.Book;
import quan.hust.itjapanese.domain.QAuditable;
import quan.hust.itjapanese.domain.QBook;
import quan.hust.itjapanese.dto.filter.BookFilter;
import quan.hust.itjapanese.utils.DataFilterUtils;

public class BookRepositoryCustomImpl implements BookRepositoryCustom
{
  @PersistenceContext
  private EntityManager em;

  @Override
  public List<Book> getBooksByFilter(BookFilter filter, OrderSpecifier<String>[] orderSpecifiers, Integer size,
    Integer page)
  {

    size = DataFilterUtils.resolveSize(size);
    page = DataFilterUtils.resolvePage(page);

    JPAQuery<?> query = new JPAQuery<>(em);

    QBook qBook = QBook.book;

    BooleanBuilder whereClause = buildWhereClause(filter);

    return query.select(qBook)
      .from(qBook)
      .where(whereClause)
      .orderBy(orderSpecifiers)
      .limit(size)
      .offset((long)page*size)
      .fetch();
  }

  @Override
  public long count(BookFilter filter)
  {
    JPAQuery<?> query = new JPAQuery<>(em);

    QBook qBook = QBook.book;

    BooleanBuilder whereClause = buildWhereClause(filter);

    Long count = query.select(qBook.count())
      .from(qBook)
      .where(whereClause)
      .fetchOne();
    return count != null ? count : 0;
  }

  private BooleanBuilder buildWhereClause(BookFilter filter)
  {
    QBook qBook = QBook.book;
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    Integer id = filter.getId();
    if (id != null)
    {
      booleanBuilder.and(qBook.Id.eq(id));
    }

    String author = filter.getAuthor();
    if(StringUtils.hasText(author))
    {
      booleanBuilder.and(qBook.author.equalsIgnoreCase(filter.getAuthor()));
    }

    String category = filter.getCategory();
    if(StringUtils.hasText(category))
    {
      booleanBuilder.and(qBook.category.equalsIgnoreCase(category));
    }

    String level = filter.getLevel();
    if(StringUtils.hasText(level))
    {
      booleanBuilder.and(qBook.level.equalsIgnoreCase(filter.getLevel()));
    }

    String text = filter.getText();
    if(StringUtils.hasText(text))
    {
      booleanBuilder.and(qBook.name.contains(filter.getText()));
    }

    Double minPrice = filter.getMinPrice();
   if(minPrice!=null)
   {
     booleanBuilder.and(qBook.price.goe(minPrice));
   }

   Double maxPrice = filter.getMaxPrice();
   if(maxPrice!=null)
   {
     booleanBuilder.and(qBook.price.loe(maxPrice));
   }

    return booleanBuilder;
  }
}
