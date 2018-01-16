package com.pictogram.pictogram.rest.controller;

import com.pictogram.pictogram.rest.model.Post;
import com.pictogram.pictogram.rest.repository.PostRepository;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@RestController
public class PostController {

  @Autowired
  PostRepository postRepository;

  @RequestMapping(value = "/post", method = RequestMethod.POST)
  public ResponseEntity<?> createPost(@RequestBody Post post) {
    System.out.println(post.toString());

    return ResponseEntity.ok("Post successfully created");
  }

  @RequestMapping(value = "/post", method = RequestMethod.GET)
  public ResponseEntity<?> getPosts() {
    List<Post> posts = postRepository.findAll();

    return ResponseEntity.ok(posts);
  }

}
