package quan.hust.itjapanese.services;

import quan.hust.itjapanese.dto.ActivityDto;
import quan.hust.itjapanese.dto.request.UserActivityRequest;
import quan.hust.itjapanese.dto.response.ManipulateCommentResponse;

public interface UserActivityService
{
  ManipulateCommentResponse addActivity(UserActivityRequest request);
  String checkHadActivity(Integer cmdId);
}
