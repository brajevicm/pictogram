package com.pictogram.pictogram.controller;

import com.pictogram.pictogram.dto.CommentDto;
import com.pictogram.pictogram.exception.comment.CommentNotFoundException;
import com.pictogram.pictogram.exception.comment.CommentsNotFoundException;
import com.pictogram.pictogram.exception.user.UserNotAuthorizedException;
import com.pictogram.pictogram.model.Comment;
import com.pictogram.pictogram.service.CommentService;
import com.pictogram.pictogram.util.BooleanUtil;
import com.pictogram.pictogram.util.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@RestController
public class CommentController {

  @Autowired
  CommentService commentService;

  @PostMapping(value = "posts/{postId}/comments")
  public ResponseEntity<Comment> createPost(@PathVariable Long postId,
                                            @RequestBody CommentDto commentDto) {
    Comment comment = commentDto.toEntityObject();

    comment = commentService.save(comment, postId);
    Long commentId = comment.getId();

    return ResponseEntity.ok(NullUtil.ifNullThrow(comment, new CommentNotFoundException(commentId)));
  }

  @GetMapping(value = "posts/{postId}/comments")
  public ResponseEntity<List<Comment>> getCommentsForPost(@PathVariable Long postId,
                                                          @RequestParam int page,
                                                          @RequestParam int size) {
    String entity = "Post";
    List<Comment> comments = commentService.findAllByPost(postId, page, size);

    return ResponseEntity.ok(NullUtil.ifNullThrow(comments, new CommentsNotFoundException(entity)));
  }

  @GetMapping(value = "users/{userId}/comments")
  public ResponseEntity<List<Comment>> getCommentsForUser(@PathVariable Long userId,
                                                          @RequestParam int page,
                                                          @RequestParam int size) {
    String entity = "User";
    List<Comment> comments = commentService.findAllByUser(userId, page, size);

    return ResponseEntity.ok(NullUtil.ifNullThrow(comments, new CommentsNotFoundException(entity)));
  }

  @DeleteMapping(value = "comments/{commentId}")
  public ResponseEntity<Boolean> deleteComment(@PathVariable Long commentId) {

    Boolean isDeleted = commentService.delete(commentId);

    return ResponseEntity.ok(BooleanUtil.ifFalseThrow(isDeleted, new UserNotAuthorizedException()));
  }
}
