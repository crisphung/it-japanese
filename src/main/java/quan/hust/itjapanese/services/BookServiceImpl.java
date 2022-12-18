package quan.hust.itjapanese.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.querydsl.core.types.OrderSpecifier;
import quan.hust.itjapanese.converter.BookConverter;
import quan.hust.itjapanese.converter.CommentConverter;
import quan.hust.itjapanese.domain.Book;
import quan.hust.itjapanese.domain.BookRating;
import quan.hust.itjapanese.domain.User;
import quan.hust.itjapanese.domain.UserBookID;
import quan.hust.itjapanese.dto.BookDto;
import quan.hust.itjapanese.dto.filter.BookFilter;
import quan.hust.itjapanese.dto.orderutil.BookOrderUtil;
import quan.hust.itjapanese.dto.request.AddBookRequest;
import quan.hust.itjapanese.dto.request.GetBookRequest;
import quan.hust.itjapanese.dto.request.RatingRequest;
import quan.hust.itjapanese.dto.response.AddBookResponse;
import quan.hust.itjapanese.dto.response.CommonResponse;
import quan.hust.itjapanese.dto.response.CompareBookResponse;
import quan.hust.itjapanese.dto.response.GetBookResponse;
import quan.hust.itjapanese.exception.InvalidSortColumnException;
import quan.hust.itjapanese.exception.InvalidSortOrderException;
import quan.hust.itjapanese.repositories.BookRatingRepository;
import quan.hust.itjapanese.repositories.BookRepository;
import quan.hust.itjapanese.utils.CSVHelper;
import quan.hust.itjapanese.utils.SecurityUtils;

@Service
public class BookServiceImpl implements BookService
{

  private static final String BOOK_CONTENT_DELIMITER = ",";
  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private BookRatingRepository brRepository;

  @Autowired
  private BookConverter converter;

  @Autowired
  private CommentConverter commentConverter;

  @Autowired
  private CSVHelper csvHelper;

  @Override
  public AddBookResponse addBook(AddBookRequest request)
  {
    AddBookResponse response;
    Book book = Book.builder()
      .level(request.getLevel())
      .price(request.getPrice())
      .author(request.getAuthor())
      .star(request.getStar())
      .name(request.getName())
      .category(request.getCategory())
      .build();

    book = bookRepository.save(book);

    return AddBookResponse.builder()
      .message("Add Success!")
      .build();
  }

  @Override
  public GetBookResponse getAllBooks(GetBookRequest request)
  {
    GetBookResponse response;
    try
    {
      Integer size = request.getSize();
      Integer page = request.getPage();

      BookFilter filter = BookFilter.builder()
        .id(request.getId())
        .minPrice(request.getMinPrice())
        .maxPrice(request.getMaxPrice())
        .author(request.getAuthor())
        .category(request.getCategory())
        .level(request.getLevel())
        .text(request.getText())
        .build();

      OrderSpecifier<String>[] orders = BookOrderUtil.createOrderBy(request.getOrderBy());
      List<Book> books = bookRepository.getBooksByFilter(filter, orders, size, page);
      List<BookDto> bookDtos = converter.convertToDtoList(books);

      long count = bookRepository.count(filter);

      response = GetBookResponse.builder()
        .books(bookDtos)
        .total(count)
        .build();
    }
    catch (InvalidSortOrderException | InvalidSortColumnException e)
    {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid sort");
    }

    return response;
  }

  @Override
  public boolean importFromFile(MultipartFile file)
  {
    List<Book> books = csvHelper.csvToBooks(file);
    bookRepository.saveAll(books);
    return true;
  }

  @Override
  public CompareBookResponse compare(Integer id1, Integer id2)
  {
    CompareBookResponse response = null;
    Optional<Book> book1Opt = bookRepository.findById(id1);
    Optional<Book> book2Opt = bookRepository.findById(id2);

    if (book1Opt.isPresent() && book2Opt.isPresent())
    {
      Book book1 = book1Opt.get();
      Book book2 = book2Opt.get();

      BookDto book1Dto = converter.convertToDto(book1);
      BookDto book2Dto = converter.convertToDto(book2);
      response = CompareBookResponse.builder()
        .firstBook(book1Dto)
        .secondBook(book2Dto)
        .build();
    }
    return response;
  }

  @Override
  @Transactional
  public CommonResponse rating(RatingRequest request)
  {
    Integer bookId = request.getBookId();
    Integer star = request.getStar() != null ? request.getStar() : 0;

    if (bookId == null)
    {
      return CommonResponse.builder()
        .error("Book id not valid!")
        .build();
    }

    Optional<Book> bookOpt = bookRepository.findById(bookId);
    if (!bookOpt.isPresent())
    {
      return CommonResponse.builder()
        .error("Book id not valid!")
        .build();
    }

    Book book = bookOpt.get();

    // Increase rate times to 1
    Integer rateTimes = book.getRateTimes() + 1;
    book.setRateTimes(rateTimes);
    Integer totalStar = book.getStar() + star;
    book.setStar(totalStar);
    book = bookRepository.save(book);

    //Update rating info
    Optional<User> userOpt = SecurityUtils.getCurrentUser();
    if (userOpt.isPresent())
    {
      User user = userOpt.get();
      UserBookID ubId = UserBookID.builder()
        .userId(user.getId())
        .bookId(bookId)
        .build();

      BookRating bookRating = BookRating.builder()
        .id(ubId)
        .star(star)
        .build();
      bookRating = brRepository.save(bookRating);
    }
    return CommonResponse.builder()
      .message("Rating successfully!")
      .build();
  }

  @Override
  public Boolean isRated(Integer bookId)
  {
    Optional<User> userOpt = SecurityUtils.getCurrentUser();
    if (userOpt.isPresent())
    {
      User user = userOpt.get();
      UserBookID ubId = UserBookID.builder()
        .userId(user.getId())
        .bookId(bookId)
        .build();


      Optional<BookRating> bookRating = brRepository.findById(ubId);

      return bookRating.isPresent();
    }
    return false;
  }

}
