package com.pictogram.pictogram.rest.controller;

import com.pictogram.pictogram.rest.model.Post;
import com.pictogram.pictogram.rest.model.dto.PostDto;
import com.pictogram.pictogram.rest.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

  @PostMapping(value = "posts", consumes = {"multipart/form-data"})
  public ResponseEntity<String> createPost(@RequestParam String title,
                                      @RequestParam String description,
                                      @RequestParam MultipartFile file) {

    PostDto postDto = new PostDto(title, description, file);

    postService.save(postDto);

    return ResponseEntity.ok("Post successfully created");
  }

  @GetMapping(value = "posts")
  public ResponseEntity<Page<Post>> getPagedPosts(@RequestParam String type,
                                      @RequestParam int page,
                                      @RequestParam int size) {
    Page<Post> posts =
      postService.findAllByType(type, page, size);

    return ResponseEntity.ok(posts);
  }

  @GetMapping(value = "/posts/{postId}")
  public ResponseEntity<Post> getPost(@PathVariable Long postId) {
    Post post = postService.findOne(postId);

    if (post == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(post);
  }

  @GetMapping(value = "users/{userId}/posts")
  public ResponseEntity<Page<Post>> getPostsFromUser(@PathVariable Long userId,
                                            @RequestParam int page,
                                            @RequestParam int size) {
    Page<Post> posts = postService.findAllByUser(userId, page, size);

    return ResponseEntity.ok(posts);
  }

}
