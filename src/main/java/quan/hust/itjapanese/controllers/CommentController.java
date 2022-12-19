package quan.hust.itjapanese.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import quan.hust.itjapanese.dto.request.CommentRequest;
import quan.hust.itjapanese.dto.response.GetCommentResponse;
import quan.hust.itjapanese.dto.response.ManipulateCommentResponse;
import quan.hust.itjapanese.services.CommentService;

@RestController
public class CommentController implements CommentOperations
{

  @Autowired
  private CommentService commentService;

  @Override
  public ManipulateCommentResponse comment(CommentRequest request)
  {

    return commentService.comment(request);
  }

  @Override
  public ManipulateCommentResponse deleteComment(Integer id)
  {
    ManipulateCommentResponse response;
    if (id == null)
    {
      return ManipulateCommentResponse.builder().error("Delete failed!").build();
    }

    return commentService.deleteComment(id);

  }

  @Override
  public ManipulateCommentResponse updateComment(CommentRequest request)
  {
    ManipulateCommentResponse response;
    Integer commentId = request.getCommentId();
    if (commentId == null)
    {
      return ManipulateCommentResponse.builder().error("Update failed!").build();
    }
    commentService.updateComment(request);
    return ManipulateCommentResponse.builder().error("Updated successful!").build();
  }

  @Override
  public ResponseEntity<GetCommentResponse> getComments(Integer bookId)
  {
    return ResponseEntity.ok(commentService.getComments(bookId));

  }
}
