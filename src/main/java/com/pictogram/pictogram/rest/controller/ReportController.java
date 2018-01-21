package com.pictogram.pictogram.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
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
public class ReportController {

  @PatchMapping(value = "posts/{postId}")
  public ResponseEntity<String> reportPost(@PathVariable Long postId) {

    return ResponseEntity.ok("");
  }

  @PatchMapping(value = "comments/{commentId}")
  public ResponseEntity<String> reportComment(@PathVariable Long commentId) {

    return ResponseEntity.ok("");
  }

}
