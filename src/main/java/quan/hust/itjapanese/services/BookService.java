package quan.hust.itjapanese.services;

import org.springframework.web.multipart.MultipartFile;

import quan.hust.itjapanese.dto.request.RatingRequest;
import quan.hust.itjapanese.dto.response.CommonResponse;
import quan.hust.itjapanese.dto.response.CompareBookResponse;
import quan.hust.itjapanese.dto.request.AddBookRequest;
import quan.hust.itjapanese.dto.request.GetBookRequest;
import quan.hust.itjapanese.dto.response.AddBookResponse;
import quan.hust.itjapanese.dto.response.GetBookResponse;

public interface BookService
{
  AddBookResponse addBook(AddBookRequest request);

  GetBookResponse getAllBooks(GetBookRequest request);

  boolean importFromFile(MultipartFile file);

  CompareBookResponse compare(Integer id1, Integer id2);

  CommonResponse rating(RatingRequest request);


}
