package com.pictogram.pictogram.rest.controller;

import com.pictogram.pictogram.rest.model.dto.PostDto;
import com.pictogram.pictogram.rest.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@RestController
public class PostController {

  @Autowired
  PostService postService;

  @RequestMapping(value = "posts/add",
    method = RequestMethod.POST, consumes = {"multipart/form-data"})
  public ResponseEntity<?> createPost(@RequestParam String title,
                                      @RequestParam String description,
                                      @RequestParam MultipartFile file) {

    PostDto postDto = new PostDto(title, description, file);

    postService.save(postDto);

    return ResponseEntity.ok("Post successfully created");
  }
}
