package quan.hust.itjapanese.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import quan.hust.itjapanese.dto.response.GetCommentResponse;
import quan.hust.itjapanese.dto.request.AddBookRequest;
import quan.hust.itjapanese.dto.request.GetBookRequest;
import quan.hust.itjapanese.dto.response.AddBookResponse;
import quan.hust.itjapanese.dto.response.GetBookResponse;
import quan.hust.itjapanese.services.BookService;

@RestController
public class BookController implements BookOperations
{
  @Autowired
  private BookService bookService;

  @Override
  public ResponseEntity<AddBookResponse> addBook(AddBookRequest request)
  {
    return ResponseEntity.ok(bookService.addBook(request));
  }

  @Override
  public GetBookResponse getBooks(
    Integer id,
    String text,
    Double minPrice,
    Double maxPrice,
    String level,
    Integer ranking,
    String category,
    String[] orderBy
  )
  {
    GetBookRequest request = GetBookRequest.builder()
      .id(id)
      .text(text)
      .minPrice(minPrice)
      .maxPrice(maxPrice)
      .category(category)
      .level(level)
      .ranking(ranking)
      .orderBy(orderBy)
      .build();

    return bookService.getAllBooks(request);
  }


  @Override
  public ResponseEntity importBooks(MultipartFile multipartFile)
  {
    bookService.importFromFile(multipartFile);
    return ResponseEntity.ok().build();
  }
}
