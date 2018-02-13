package com.pictogram.pictogram.controller;

import com.pictogram.pictogram.dto.PostDto;
import com.pictogram.pictogram.exception.post.PostNotFoundException;
import com.pictogram.pictogram.exception.post.PostsNotFoundException;
import com.pictogram.pictogram.exception.user.UserNotAuthorizedException;
import com.pictogram.pictogram.exception.user.UserNotFoundException;
import com.pictogram.pictogram.model.Post;
import com.pictogram.pictogram.model.User;
import com.pictogram.pictogram.service.PostService;
import com.pictogram.pictogram.service.UserService;
import com.pictogram.pictogram.storage.StorageService;
import com.pictogram.pictogram.util.BooleanUtil;
import com.pictogram.pictogram.util.EmptyUtil;
import com.pictogram.pictogram.util.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
  private PostService postService;

  @Autowired
  private UserService userService;

  @Autowired
  private StorageService storageService;

  @PostMapping(value = "posts", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Post> createPost(@RequestParam String title,
                                         @RequestParam String description,
                                         @RequestParam MultipartFile file) {
    String profileImage = storageService.store(file);

    Post post = new Post();
    post.setTitle(title);
    post.setDescription(description);
    post.setPostImage(profileImage);
    post = postService.save(post);
    Long postId = post.getId();

    return ResponseEntity
      .ok(NullUtil.ifNullThrow(
        post, new PostNotFoundException(postId)
      ));
  }

  @PostMapping(value = "posts", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Post> createPost(@RequestBody PostDto postDto) {
    Post post = new Post();
    post.setTitle(postDto.getTitle());
    post.setDescription(postDto.getDescription());
    post.setPostImage(postDto.getPostImage());
    post = postService.save(post);
    Long postId = post.getId();

    return ResponseEntity
      .ok(NullUtil.ifNullThrow(
        post, new PostNotFoundException(postId)
      ));
  }

  @GetMapping(value = "posts")
  public ResponseEntity<List<Post>> getPosts(@RequestParam String type,
                                             @PageableDefault(
                                               value = Integer.MAX_VALUE,
                                               direction = Sort.Direction.DESC,
                                               sort = "createdDate") Pageable pageable) {
    List<Post> posts = EmptyUtil.ifEmptyThrow(postService.findAllByType(type, pageable), new PostsNotFoundException());

    return ResponseEntity
      .ok(EmptyUtil.ifEmptyThrow(
        posts, new PostsNotFoundException()
      ));
  }

  @GetMapping(value = "posts/{postId}")
  public ResponseEntity<Post> getPost(@PathVariable Long postId) {
    Post post = NullUtil.ifNullThrow(postService.findOne(postId), new PostNotFoundException(postId));

    return ResponseEntity
      .ok(NullUtil.ifNullThrow(
        post, new PostNotFoundException(postId)
      ));
  }

  @GetMapping(value = "users/{userId}/posts")
  public ResponseEntity<List<Post>> getPostsFromUser(@PathVariable Long userId,
                                                     @PageableDefault(value = Integer.MAX_VALUE)
                                                       Pageable pageable) {
    User user = NullUtil.ifNullThrow(userService.findOne(userId), new UserNotFoundException(userId));
    List<Post> postsFromUser = postService.findAllByUser(user, pageable);
    return ResponseEntity
      .ok(EmptyUtil.ifEmptyThrow(
        postsFromUser, new PostsNotFoundException()
      ));
  }

  @GetMapping(value = "followers/posts")
  public ResponseEntity<List<Post>> getPostsFromFollows(@PageableDefault(value = Integer.MAX_VALUE)
                                                          Pageable pageable) {
    User user = NullUtil.ifNullThrow(userService.getCurrentUser(), new UserNotAuthorizedException());
    List<Post> postsFromFollows = postService.findAllByFollows(user, pageable);

    return ResponseEntity
      .ok(EmptyUtil.ifEmptyThrow(
        postsFromFollows, new PostsNotFoundException()
      ));
  }

  @DeleteMapping(value = "posts/{postId}")
  public ResponseEntity<Boolean> deleteComment(@PathVariable Long postId) {
    Post post = NullUtil.ifNullThrow(postService.findOne(postId), new PostNotFoundException(postId));

    return ResponseEntity
      .ok(BooleanUtil.ifFalseThrow(
        postService.delete(post),
        new UserNotAuthorizedException()
      ));
  }

}
