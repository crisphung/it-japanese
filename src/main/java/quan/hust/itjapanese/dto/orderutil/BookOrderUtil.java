package quan.hust.itjapanese.dto.orderutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import com.querydsl.core.types.OrderSpecifier;
import quan.hust.itjapanese.domain.Book;
import quan.hust.itjapanese.domain.QBook;
import quan.hust.itjapanese.exception.InvalidSortColumnException;
import quan.hust.itjapanese.exception.InvalidSortOrderException;
import quan.hust.itjapanese.utils.DataFilterConstants;
import quan.hust.itjapanese.utils.OrderUtils;

public final class BookOrderUtil
{
  private static final List<String> SORTABLE_COLUMNS = Arrays.asList(Book.ColumnName.PRICE, Book.ColumnName.RANKING);

  private BookOrderUtil()
  {
  }

  public static OrderSpecifier<String>[] createOrderBy(String[] orderByColumns)
    throws InvalidSortOrderException, InvalidSortColumnException
  {
    String entityName = QBook.book.getMetadata().getName();
    if (orderByColumns == null || orderByColumns.length == 0)
    {
      orderByColumns = new String[] {
        Book.ColumnName.PRICE + ":" + DataFilterConstants.Sorting.DESC
      };
      return OrderUtils.createOrderBy(entityName, orderByColumns, SORTABLE_COLUMNS);
    }

    return OrderUtils.createOrderBy(entityName, orderByColumns, SORTABLE_COLUMNS);
  }
}
