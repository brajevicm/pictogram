package com.pictogram.pictogram.rest.controller;

import com.pictogram.pictogram.commons.exception.UserAlreadyUpvotedException;
import com.pictogram.pictogram.rest.service.CommentService;
import com.pictogram.pictogram.rest.service.PostService;
import com.pictogram.pictogram.rest.service.UpvoteService;
import com.pictogram.pictogram.rest.service.UserService;
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

  @Autowired
  PostService postService;

  @Autowired
  CommentService commentService;

  @Autowired
  UserService userService;

  @PutMapping(value = "posts/{postId}")
  public ResponseEntity<String> upvotePost(@PathVariable Long postId) {
    if (postService.findOne(postId).getUpvotePosts().stream()
      .anyMatch(upvotePost ->
        upvotePost.getUser().getUsername().equals(userService.getCurrentUser().getUsername()))) {
      throw new UserAlreadyUpvotedException("Post was already upvoted");
    }

    upvoteService.savePost(postId);

    return ResponseEntity.ok("Post successfully upvoted");
  }

  @PutMapping(value = "comments/{commentId}")
  public ResponseEntity<String> upvoteComment(@PathVariable Long commentId) {
    if (commentService.findOne(commentId).getUpvoteComments().stream()
      .anyMatch(upvoteComment ->
        upvoteComment.getUser().getUsername().equals(userService.getCurrentUser().getUsername()))) {
      throw new UserAlreadyUpvotedException("Comment was already upvoted");
    }

    upvoteService.saveComment(commentId);

    return ResponseEntity.ok("Comment successfully upvoted");
  }

}
