package com.pictogram.pictogram.controller;

import com.pictogram.pictogram.dto.CommentDto;
import com.pictogram.pictogram.exception.comment.CommentNotFoundException;
import com.pictogram.pictogram.exception.comment.CommentsNotFoundException;
import com.pictogram.pictogram.exception.post.PostNotFoundException;
import com.pictogram.pictogram.exception.user.UserNotAuthorizedException;
import com.pictogram.pictogram.exception.user.UserNotFoundException;
import com.pictogram.pictogram.model.Comment;
import com.pictogram.pictogram.service.CommentService;
import com.pictogram.pictogram.service.PostService;
import com.pictogram.pictogram.service.UserService;
import com.pictogram.pictogram.util.BooleanUtil;
import com.pictogram.pictogram.util.EmptyUtil;
import com.pictogram.pictogram.util.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
  private CommentService commentService;

  @Autowired
  private UserService userService;

  @Autowired
  private PostService postService;

  @PostMapping(value = "posts/{postId}/comments")
  public ResponseEntity<Comment> createPost(@PathVariable Long postId,
                                            @RequestBody CommentDto commentDto) {
    NullUtil.ifNullThrow(postService.findOne(postId), new PostNotFoundException(postId));
    Comment comment = commentDto.toEntityObject();

    comment = commentService.save(comment, postId);
    Long commentId = comment.getId();

    return ResponseEntity
      .ok(NullUtil.ifNullThrow(
        comment, new CommentNotFoundException(commentId)
      ));
  }

  @GetMapping(value = "posts/{postId}/comments")
  public ResponseEntity<List<Comment>> getCommentsForPost(@PathVariable Long postId,
                                                          @PageableDefault(value = Integer.MAX_VALUE)
                                                            Pageable pageable) {
    NullUtil.ifNullThrow(postService.findOne(postId), new PostNotFoundException(postId));
    String entity = "Post";
    List<Comment> comments = commentService.findAllByPost(postId, pageable);

    return ResponseEntity
      .ok(EmptyUtil.ifEmptyThrow(
        comments, new CommentsNotFoundException(entity)
      ));
  }

  @GetMapping(value = "users/{userId}/comments")
  public ResponseEntity<List<Comment>> getCommentsForUser(@PathVariable Long userId,
                                                          @PageableDefault(
                                                            sort = "createdDate",
                                                            direction = Sort.Direction.DESC,
                                                            value = Integer.MAX_VALUE) Pageable pageable) {
    NullUtil.ifNullThrow(userService.findOne(userId), new UserNotFoundException(userId));
    String entity = "User";
    List<Comment> comments = commentService.findAllByUser(userId, pageable);

    return ResponseEntity
      .ok(EmptyUtil.ifEmptyThrow(
        comments, new CommentsNotFoundException(entity)
      ));
  }

  @DeleteMapping(value = "comments/{commentId}")
  public ResponseEntity<Boolean> deleteComment(@PathVariable Long commentId) {
    Comment comment = NullUtil.ifNullThrow(commentService.findOne(commentId), new CommentNotFoundException(commentId));

    return ResponseEntity
      .ok(BooleanUtil.ifFalseThrow(
        commentService.delete(comment),
        new UserNotAuthorizedException()
      ));
  }
}
