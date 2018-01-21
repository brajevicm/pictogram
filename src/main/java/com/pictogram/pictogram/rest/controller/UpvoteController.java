package com.pictogram.pictogram.rest.controller;

import com.pictogram.pictogram.rest.service.UpvoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project: pictogram
 * Date: 21-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@RestController
public class UpvoteController {

  @Autowired
  UpvoteService upvoteService;

  @PutMapping(value = "posts/{postId}")
  public ResponseEntity<String> upvotePost(@PathVariable Long postId) {
    upvoteService.savePost(postId);

    return ResponseEntity.ok("Post successfully upvoted");
  }

  @PutMapping(value = "comments/{commentId}")
  public ResponseEntity<String> upvoteComment(@PathVariable Long commentId) {
    upvoteService.saveComment(commentId);

    return ResponseEntity.ok("Comment successfully upvoted");
  }

}
