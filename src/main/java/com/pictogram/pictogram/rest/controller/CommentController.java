package com.pictogram.pictogram.rest.controller;

import com.pictogram.pictogram.rest.model.dto.CommentDto;
import com.pictogram.pictogram.rest.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
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

  @RequestMapping(value = "/posts/{id}/comments", method = RequestMethod.POST)
  public ResponseEntity<?> createPost(@PathVariable("id") Long id,
                                      @RequestBody CommentDto commentDto) {
    commentDto.setPostId(id);
    commentService.save(commentDto);

    return ResponseEntity.ok("Comment successfully created");
  }




}
