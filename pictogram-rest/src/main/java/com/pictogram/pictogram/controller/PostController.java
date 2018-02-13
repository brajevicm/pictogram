package com.pictogram.pictogram.controller;

import com.pictogram.pictogram.dto.PostDto;
import com.pictogram.pictogram.exception.post.PostNotFoundException;
import com.pictogram.pictogram.exception.post.PostsNotFoundException;
import com.pictogram.pictogram.exception.user.UserNotAuthorizedException;
import com.pictogram.pictogram.exception.user.UserNotFoundException;
import com.pictogram.pictogram.model.Post;
import com.pictogram.pictogram.service.PostService;
import com.pictogram.pictogram.service.UserService;
import com.pictogram.pictogram.util.EmptyUtil;
import com.pictogram.pictogram.util.NullUtil;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
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
  Log logger = LogFactory.getLog(PostController.class);


  @Autowired
  private PostService postService;

  @Autowired
  private UserService userService;

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
  public ResponseEntity<List<Post>> getPosts(@RequestParam String type,
                                             @RequestParam int page,
                                             @RequestParam int size) {
    List<Post> posts = postService.findAllByType(type, page, size);

    return ResponseEntity.ok(EmptyUtil.ifEmptyThrow(posts, new PostsNotFoundException()));
  }

  @GetMapping(value = "posts/{postId}")
  public ResponseEntity<Post> getPost(@PathVariable Long postId) {
    Post post = postService.findOne(postId);

    return ResponseEntity.ok(NullUtil.ifNullThrow(post, new PostNotFoundException(postId)));
  }

  @GetMapping(value = "users/{userId}/posts")
  public ResponseEntity<List<Post>> getPostsFromUser(@PathVariable Long userId,
                                                     @RequestParam int page,
                                                     @RequestParam int size) {
    NullUtil.ifNullThrow(userService.findOne(userId), new UserNotFoundException(userId));
    List<Post> posts = postService.findAllByUser(userId, page, size);

    return ResponseEntity.ok(EmptyUtil.ifEmptyThrow(posts, new PostsNotFoundException()));
  }

  @GetMapping(value = "followers/posts")
  public ResponseEntity<List<Post>> getPostsFromFollows(@RequestParam int page,
                                                        @RequestParam int size) {
    NullUtil.ifNullThrow(userService.getCurrentUser(), new UserNotAuthorizedException());

    List<Post> posts = postService.findAllByFollows(page, size);

    return ResponseEntity.ok(EmptyUtil.ifEmptyThrow(posts, new PostsNotFoundException()));
  }

}
