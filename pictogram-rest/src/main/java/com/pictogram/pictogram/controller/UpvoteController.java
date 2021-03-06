package com.pictogram.pictogram.controller;

import com.pictogram.pictogram.exception.user.UserAlreadyUpvotedException;
import com.pictogram.pictogram.model.Comment;
import com.pictogram.pictogram.model.Post;
import com.pictogram.pictogram.service.CommentService;
import com.pictogram.pictogram.service.PostService;
import com.pictogram.pictogram.service.UpvoteService;
import com.pictogram.pictogram.service.UserService;
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
  private UpvoteService upvoteService;

  @Autowired
  private PostService postService;

  @Autowired
  private CommentService commentService;

  @Autowired
  private UserService userService;

  @PutMapping(value = "posts/{postId}")
  public ResponseEntity<Post> upvotePost(@PathVariable Long postId) {
    if (postService.findOne(postId).getUpvotePosts().stream()
      .anyMatch(upvotePost ->
        upvotePost.getUser().getUsername().equals(userService.getCurrentUser().getUsername()))) {
      throw new UserAlreadyUpvotedException("Post was already upvoted");
    }

    upvoteService.savePost(postId);

    return ResponseEntity.ok(null);
  }

  @PutMapping(value = "comments/{commentId}")
  public ResponseEntity<Comment> upvoteComment(@PathVariable Long commentId) {
    if (commentService.findOne(commentId).getUpvoteComments().stream()
      .anyMatch(upvoteComment ->
        upvoteComment.getUser().getUsername().equals(userService.getCurrentUser().getUsername()))) {
      throw new UserAlreadyUpvotedException("Comment was already upvoted");
    }

    upvoteService.saveComment(commentId);

    return ResponseEntity.ok(null);
  }

}
