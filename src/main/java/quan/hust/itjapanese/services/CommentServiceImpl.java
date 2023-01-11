package quan.hust.itjapanese.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import quan.hust.itjapanese.converter.CommentConverter;
import quan.hust.itjapanese.domain.Book;
import quan.hust.itjapanese.domain.BookRating;
import quan.hust.itjapanese.domain.Comment;
import quan.hust.itjapanese.domain.User;
import quan.hust.itjapanese.domain.UserBookID;
import quan.hust.itjapanese.dto.CommentDto;
import quan.hust.itjapanese.dto.request.CommentRequest;
import quan.hust.itjapanese.dto.response.GetCommentResponse;
import quan.hust.itjapanese.dto.response.ManipulateCommentResponse;
import quan.hust.itjapanese.repositories.BookRepository;
import quan.hust.itjapanese.repositories.CommentRepository;
import quan.hust.itjapanese.utils.SecurityUtils;

@Component
public class CommentServiceImpl implements CommentService
{
  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private CommentConverter commentConverter;

  @Override
  public ManipulateCommentResponse comment(CommentRequest request)
  {
    Integer bookId = request.getBookId();
    Integer star = request.getStar();
    Optional<Book> bookOpt = bookRepository.findById(bookId);
    if (bookOpt.isPresent())
    {
      Book book = bookOpt.get();
      // Increase rate times to 1
      Integer rateTimes = book.getRateTimes() + 1;
      book.setRateTimes(rateTimes);
      Integer totalStar = book.getStar() + request.getStar();
      book.setStar(totalStar);

      book = bookRepository.save(book);


      Comment comment = Comment.builder()
        .content(request.getContent())
        .book(book)
        .star(star)
        .like(0)
        .dislike(0)
        .build();

      comment = commentRepository.save(comment);

    }
    return ManipulateCommentResponse.builder()
      .message("Comment Success!").build();

  }

  public GetCommentResponse getComments(Integer bookId)
  {
    GetCommentResponse response;
    Book book = bookRepository.findById(bookId).orElse(null);

    if (book == null)
    {
      return GetCommentResponse.builder()
        .message("No book found!")
        .build();
    }

    List<Comment> comments = commentRepository.findCommentByBookOrderByCreatedAtDesc(book);

    List<CommentDto> commentDtos = commentConverter.convertToDtoList(comments);

    return GetCommentResponse.builder().comments(commentDtos).build();
  }

  @Override
  public ManipulateCommentResponse deleteComment(Integer commentId)
  {
    Comment comment = commentRepository.findById(commentId).orElse(null);
    if (comment == null)
    {
      return ManipulateCommentResponse.builder().error("Delete Failed!").build();
    }

    commentRepository.deleteById(commentId);
    return ManipulateCommentResponse.builder().message("Deleted successful!").build();
  }

  @Override
  public ManipulateCommentResponse updateComment(CommentRequest request)
  {
    Comment comment = commentRepository.findById(request.getCommentId()).orElse(null);
    if (comment == null)
    {
      return ManipulateCommentResponse.builder().error("Update Failed!").build();
    }
    comment.setContent(request.getContent());
    commentRepository.save(comment);

    return ManipulateCommentResponse.builder().message("Updated successful!").build();
  }

  @Override
  public CommentDto getCommentDetail(Integer cmdId)
  {
    return commentConverter.convertToDto(commentRepository.findById(cmdId).get());
  }
}
