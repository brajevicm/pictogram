package com.pictogram.pictogram.controller;

import com.pictogram.pictogram.dto.PostDto;
import com.pictogram.pictogram.exception.PostNotFoundException;
import com.pictogram.pictogram.model.Post;
import com.pictogram.pictogram.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
  PostService postService;

  //  @TODO Fix image upload
  @PostMapping(value = "posts", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<String> createPost(@RequestParam String title,
                                           @RequestParam String description,
                                           @RequestParam MultipartFile file) {
    String fullImagePath = "";
    Post post = new Post();
    try {
      byte[] bytes = file.getBytes();
      Path path = Paths.get("" + file.getOriginalFilename());
      Files.write(path, bytes);
      fullImagePath = path.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    post.setTitle(title);
    post.setDescription(description);
    post.setPostImage(fullImagePath);
    postService.save(post);

    return ResponseEntity.ok("Post successfully created");
  }

  @PostMapping(value = "posts", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> createPost(@RequestBody PostDto postDto) {
    Post post = new Post();
    post.setTitle(postDto.getTitle());
    post.setDescription(postDto.getDescription());
    post.setPostImage(postDto.getPostImage());
    postService.save(post);

    return ResponseEntity.ok("Post successfully created");
  }

  @GetMapping(value = "posts")
  public ResponseEntity<List<Post>> getListdPosts(@RequestParam String type,
                                                  @RequestParam int List,
                                                  @RequestParam int size) {
    List<Post> posts = postService.findAllByType(type, List, size);

    return ResponseEntity.ok(posts);
  }

  @GetMapping(value = "posts/{postId}")
  public ResponseEntity<Post> getPost(@PathVariable Long postId) {
    Post post = postService.findOne(postId);

    if (post == null) {
      throw new PostNotFoundException(postId);
    }

    return ResponseEntity.ok(post);
  }

  @GetMapping(value = "users/{userId}/posts")
  public ResponseEntity<List<Post>> getPostsFromUser(@PathVariable Long userId,
                                                     @RequestParam int List,
                                                     @RequestParam int size) {
    List<Post> posts = postService.findAllByUser(userId, List, size);

    return ResponseEntity.ok(posts);
  }
}
