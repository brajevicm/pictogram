package com.pictogram.pictogram.rest.controller;

import com.pictogram.pictogram.rest.model.Comment;
import com.pictogram.pictogram.rest.model.dto.CommentDto;
import com.pictogram.pictogram.rest.service.CommentService;
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
    commentService.save(commentDto, postId);

    return ResponseEntity.ok("Comment successfully created");
  }

  @GetMapping(value = "posts/{postId}/comments")
  public ResponseEntity<Page<Comment>> getCommentsForPost(@PathVariable Long postId,
                                                          @RequestParam int page,
                                                          @RequestParam int size) {
    Page<Comment> comments = commentService.findAllByPost(postId, page, size);

    return ResponseEntity.ok(comments);
  }

  @GetMapping(value = "users/{userid}/comments")
  public ResponseEntity<Page<Comment>> getCommentsForUser(@PathVariable Long userId,
                                                          @RequestParam int page,
                                                          @RequestParam int size) {
    Page<Comment> comments = commentService.findAllByUser(userId, page, size);

    return ResponseEntity.ok(comments);
  }

}
