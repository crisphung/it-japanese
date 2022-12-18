package quan.hust.itjapanese.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.apache.commons.collections4.Get;
import quan.hust.itjapanese.dto.request.AddBookRequest;
import quan.hust.itjapanese.dto.request.RatingRequest;
import quan.hust.itjapanese.dto.response.AddBookResponse;
import quan.hust.itjapanese.dto.response.CommonResponse;
import quan.hust.itjapanese.dto.response.CompareBookResponse;
import quan.hust.itjapanese.dto.response.GetBookResponse;

@RequestMapping(BookOperations.RESOURCE_API)
public interface BookOperations
{
   String RESOURCE_API = "/api/book";

   String IMPORT_BOOKS ="/import";

  String COMPARE_BOOKS ="/compare";

  String RATING = "/rating";

  String IS_RATED = "/is-rated";

   @PostMapping
   ResponseEntity<AddBookResponse> addBook(AddBookRequest request);

   @Operation(summary = "Filter all books")
   @GetMapping
   GetBookResponse getBooks(
     @RequestParam(name = "id",required = false) Integer id,

     @RequestParam(name = "text",required = false) String text,

     @RequestParam(name="minPrice",required = false) Double minPrice,

     @RequestParam(name = "maxPrice", required = false) Double maxPrice,

     @RequestParam(name = "level",required = false) String level,

     @RequestParam(name="ranking",required = false) Integer ranking,

     @RequestParam(name="category",required = false) String category,

     @RequestParam(name = "orderBy",required = false) String[] orderBy,

     @RequestParam(name="size",required = false) Integer size,

     @RequestParam(name="page",required = false) Integer page
   );

  @Operation(summary = "Compare books by bookId")
  @GetMapping(COMPARE_BOOKS)
  ResponseEntity<CompareBookResponse> compare(
    @Parameter(description = "First book's id")
    @RequestParam(name = "book1") Integer id1,

    @Parameter(description = "Second book's id")
    @RequestParam(name = "book2") Integer id2
  );
  @Operation(summary = "Import books from .csv file. Return HTTP status 400 if the data is invalid")
  @PostMapping(IMPORT_BOOKS)
  ResponseEntity<Void> importBooks(
    @Parameter(description = "The csv file") @RequestParam("file") MultipartFile multipartFile
  );

  @Operation(summary = "Evaluate star for book")
  @PostMapping(RATING)
  ResponseEntity<CommonResponse> rating(@RequestBody RatingRequest request);

  @Operation(summary = "Check whether user rated this book")
  @GetMapping(IS_RATED)
  ResponseEntity<Boolean> isRated(@RequestParam(name = "bookId") Integer bookId);
}
