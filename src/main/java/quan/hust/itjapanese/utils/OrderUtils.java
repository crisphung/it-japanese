package quan.hust.itjapanese.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import org.apache.commons.lang3.text.WordUtils;
import quan.hust.itjapanese.exception.InvalidSortColumnException;
import quan.hust.itjapanese.exception.InvalidSortOrderException;

public final class OrderUtils
{

  private OrderUtils()
  {
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static OrderSpecifier<String>[] createOrderBy(String entityName, String[] orderByColumns,
    final List<String> sortableColumns) throws InvalidSortOrderException, InvalidSortColumnException
  {
    if (orderByColumns == null || entityName == null || orderByColumns.length <= 0)
    {
      return new OrderSpecifier[0];
    }

    PathBuilder<Entity> entityPathBuilder = new PathBuilder<>(Entity.class, entityName);

    ArrayList<OrderSpecifier<String>> orderSpecifiers = new ArrayList<OrderSpecifier<String>>();

    for (String orderByColumn : orderByColumns)
    {
      String[] columnOrderPair = orderByColumn.split(":");

      PathBuilder<?> pathBuilder;

      // Column
      String column = columnOrderPair[0];
      if (!sortableColumns.contains(column))
      {
        throw new InvalidSortColumnException();
      }

      String entityPath = columnNameToEntityPath(column);
      pathBuilder = entityPathBuilder.get(entityPath);

      // Order
      Order order;
      if (columnOrderPair.length == 1)
      {
        order = Order.ASC;
      }
      else if (columnOrderPair.length == 2)
      {
        String providedOrder = columnOrderPair[1];

        if (DataFilterConstants.Sorting.ASC.equalsIgnoreCase(providedOrder))
        {
          order = Order.ASC;
        }
        else if (DataFilterConstants.Sorting.DESC.equalsIgnoreCase(providedOrder))
        {
          order = Order.DESC;
        }
        else
        {
          throw new InvalidSortOrderException();
        }
      }
      else
      {
        throw new InvalidSortOrderException();
      }

      orderSpecifiers.add(new OrderSpecifier(order, pathBuilder));
    }

    return orderSpecifiers.toArray(new OrderSpecifier[0]);
  }

  private static String columnNameToEntityPath(String columnName)
  {
    String capitalizedColumnName = org.apache.commons.lang3.text.WordUtils.capitalizeFully(columnName, '_');
    String upperCamelCaseColumnName = capitalizedColumnName.replace("_", "");

    return WordUtils.uncapitalize(upperCamelCaseColumnName);
  }
}
