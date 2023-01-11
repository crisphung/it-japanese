package quan.hust.itjapanese.services;

import quan.hust.itjapanese.dto.CommentDto;
import quan.hust.itjapanese.dto.request.CommentRequest;
import quan.hust.itjapanese.dto.response.GetCommentResponse;
import quan.hust.itjapanese.dto.response.ManipulateCommentResponse;

public interface CommentService
{
  ManipulateCommentResponse comment(CommentRequest request);

  GetCommentResponse getComments(Integer bookId);

  ManipulateCommentResponse deleteComment(Integer commentId);

  ManipulateCommentResponse updateComment(CommentRequest request);

  CommentDto getCommentDetail(Integer cmdId);

}
