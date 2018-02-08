package com.pictogram.pictogram.controller;

import com.pictogram.pictogram.domain.CommentDomain;
import com.pictogram.pictogram.dto.CommentDto;
import com.pictogram.pictogram.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    CommentDomain comment = new CommentDomain();
    comment.setDescription(commentDto.getDescription());

    commentService.save(comment, postId);

    return ResponseEntity.ok("Comment successfully created");
  }

  @GetMapping(value = "posts/{postId}/comments")
  public ResponseEntity<Page<CommentDomain>> getCommentsForPost(@PathVariable Long postId,
                                                                @RequestParam int page,
                                                                @RequestParam int size) {
    Page<CommentDomain> comments = commentService.findAllByPost(postId, page, size);

    return ResponseEntity.ok(comments);
  }

  @GetMapping(value = "users/{userId}/comments")
  public ResponseEntity<Page<CommentDomain>> getCommentsForUser(@PathVariable Long userId,
                                                          @RequestParam int page,
                                                          @RequestParam int size) {
    Page<CommentDomain> comments = commentService.findAllByUser(userId, page, size);

    return ResponseEntity.ok(comments);
  }

  @DeleteMapping(value = "comments/{commentId}")
  public ResponseEntity<String> deletePost(@PathVariable Long commentId) {
    commentService.delete(commentId);

    return ResponseEntity.ok("Comment successfully deleted");
  }
}
