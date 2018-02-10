package com.pictogram.pictogram.controller;

import com.pictogram.pictogram.dto.CommentDto;
import com.pictogram.pictogram.model.Comment;
import com.pictogram.pictogram.service.CommentService;
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
  public ResponseEntity<String> createPost(@PathVariable Long postId,
                                           @RequestBody CommentDto commentDto) {
    Comment comment = new Comment();
    comment.setDescription(commentDto.getDescription());

    commentService.save(comment, postId);

    return ResponseEntity.ok("Comment successfully created");
  }

  @GetMapping(value = "posts/{postId}/comments")
  public ResponseEntity<List<Comment>> getCommentsForPost(@PathVariable Long postId,
                                                          @RequestParam int page,
                                                          @RequestParam int size) {
    List<Comment> comments = commentService.findAllByPost(postId, page, size);

    return ResponseEntity.ok(comments);
  }

  @GetMapping(value = "users/{userId}/comments")
  public ResponseEntity<List<Comment>> getCommentsForUser(@PathVariable Long userId,
                                                          @RequestParam int page,
                                                          @RequestParam int size) {
    List<Comment> comments = commentService.findAllByUser(userId, page, size);

    return ResponseEntity.ok(comments);
  }

  @DeleteMapping(value = "comments/{commentId}")
  public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
    commentService.delete(commentId);

    return ResponseEntity.ok("Comment successfully deleted");
  }
}
