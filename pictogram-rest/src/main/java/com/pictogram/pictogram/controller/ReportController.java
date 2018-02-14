package com.pictogram.pictogram.controller;

import com.pictogram.pictogram.exception.user.UserAlreadyReportedException;
import com.pictogram.pictogram.model.Comment;
import com.pictogram.pictogram.model.Post;
import com.pictogram.pictogram.service.CommentService;
import com.pictogram.pictogram.service.PostService;
import com.pictogram.pictogram.service.ReportService;
import com.pictogram.pictogram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project: pictogram
 * Date: 21-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@RestController
public class ReportController {

  @Autowired
  private ReportService reportService;

  @Autowired
  private PostService postService;

  @Autowired
  private CommentService commentService;

  @Autowired
  private UserService userService;

  @PatchMapping(value = "posts/{postId}")
  public ResponseEntity<Post> reportPost(@PathVariable Long postId) {
    if (postService.findOne(postId).getReportPosts().stream()
      .anyMatch(reportPost ->
        reportPost.getUser().getUsername().equals(userService.getCurrentUser().getUsername()))) {
      throw new UserAlreadyReportedException("Post was already reported");
    }

    reportService.savePost(postId);

    return ResponseEntity.ok(null);
  }

  @PatchMapping(value = "comments/{commentId}")
  public ResponseEntity<Comment> reportComment(@PathVariable Long commentId) {
    if (commentService.findOne(commentId).getReportComments().stream()
      .anyMatch(reportComment ->
        reportComment.getUser().getUsername().equals(userService.getCurrentUser().getUsername()))) {
      throw new UserAlreadyReportedException("Comment was already reported");
    }

    reportService.saveComment(commentId);

    return ResponseEntity.ok(null);
  }

}
