package com.pictogram.pictogram.rest.controller;

import com.pictogram.pictogram.rest.service.ReportService;
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
  ReportService reportService;

  @PatchMapping(value = "posts/{postId}")
  public ResponseEntity<String> reportPost(@PathVariable Long postId) {
    reportService.savePost(postId);

    return ResponseEntity.ok("Post successfully reported");
  }

  @PatchMapping(value = "comments/{commentId}")
  public ResponseEntity<String> reportComment(@PathVariable Long commentId) {
    reportService.saveComment(commentId);

    return ResponseEntity.ok("Post successfully reported");
  }

}
