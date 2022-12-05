package quan.hust.itjapanese.converter;

import java.util.ArrayList;
import java.util.List;


public interface EntityConverter<E, D>
{

  default List<D> convertToDtoList(List<E> entityList)
  {
    List<D> dtoList = new ArrayList<>();

    if (entityList != null)
    {
      entityList.forEach(entity -> {
        if (entity != null)
        {
          dtoList.add(convertToDto(entity));
        }
      });
    }

    return dtoList;
  }

  D convertToDto(E entity);

//  default E convertToEntity(D dto)
//  {
//    throw new ResourceNotFoundException("The method was not supported or not yet implemented");
//  }
}
