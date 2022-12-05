package quan.hust.itjapanese.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import quan.hust.itjapanese.dto.request.CommentRequest;
import quan.hust.itjapanese.dto.response.AddCommentResponse;
import quan.hust.itjapanese.dto.response.GetCommentResponse;
import quan.hust.itjapanese.services.CommentService;

@RestController
public class CommentController implements CommentOperations
{

  @Autowired
  private CommentService commentService;

  @Override
  public AddCommentResponse comment(CommentRequest request)
  {
    return  commentService.comment(request);
  }

  @Override
  public ResponseEntity<GetCommentResponse> getComments(Integer bookId)
  {
    return ResponseEntity.ok(commentService.getComments(bookId));

  }
}
