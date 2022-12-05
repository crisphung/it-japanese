package quan.hust.itjapanese.services;

import quan.hust.itjapanese.dto.request.CommentRequest;
import quan.hust.itjapanese.dto.response.AddCommentResponse;
import quan.hust.itjapanese.dto.response.GetCommentResponse;

public interface CommentService
{
  AddCommentResponse comment(CommentRequest request);

  GetCommentResponse getComments(Integer bookId);

}
