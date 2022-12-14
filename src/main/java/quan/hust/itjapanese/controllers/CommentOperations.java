package quan.hust.itjapanese.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import quan.hust.itjapanese.dto.request.CommentRequest;
import quan.hust.itjapanese.dto.response.GetCommentResponse;
import quan.hust.itjapanese.dto.response.ManipulateCommentResponse;

@RequestMapping(CommentOperations.RESOURCE_API)
public interface CommentOperations
{
  String RESOURCE_API = "/api/comment";

  @Operation(summary = "Comment functions")
  @PostMapping
  ManipulateCommentResponse comment(@RequestBody  CommentRequest request);

  @Operation(summary = "Delete comment functions")
  @DeleteMapping
  ManipulateCommentResponse deleteComment(@RequestBody CommentRequest request);

  @Operation(summary = "Update comment")
  @PutMapping
  ManipulateCommentResponse updateComment(@RequestBody CommentRequest request);

  @Operation(summary = "List all comment by bookIds")
  @GetMapping
  ResponseEntity<GetCommentResponse> getComments(@RequestParam(name = "bookId",required = true) Integer bookId);
}
