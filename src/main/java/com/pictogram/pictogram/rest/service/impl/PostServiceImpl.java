package com.pictogram.pictogram.rest.service.impl;

import com.pictogram.pictogram.commons.storage.StorageService;
import com.pictogram.pictogram.commons.utils.TimeProvider;
import com.pictogram.pictogram.rest.model.Post;
import com.pictogram.pictogram.rest.model.User;
import com.pictogram.pictogram.rest.model.dto.PostDto;
import com.pictogram.pictogram.rest.model.report.ReportPost;
import com.pictogram.pictogram.rest.model.upvote.UpvotePost;
import com.pictogram.pictogram.rest.repository.PostRepository;
import com.pictogram.pictogram.rest.service.CommentService;
import com.pictogram.pictogram.rest.service.PostService;
import com.pictogram.pictogram.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project: pictogram
 * Date: 17-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Service
public class PostServiceImpl implements PostService {

  private static final String FRESH = "fresh";
  private static final String HOT = "hot";
  private static final String TRENDING = "trending";
  private static final String REPORTED = "reported";

  @Autowired
  PostRepository postRepository;

  @Autowired
  TimeProvider timeProvider;

  @Autowired
  StorageService storageService;

  @Autowired
  UserService userService;

  @Autowired
  CommentService commentService;

  @Override
  public void save(PostDto postDto) {
    String postImage;

    if (postDto.getFile() == null) {
      postImage = postDto.getPostImage();
    } else {
      postImage = storageService.store(postDto.getFile());
    }

    Post post = new Post(
      postDto.getTitle(),
      postDto.getDescription(),
      postImage,
      timeProvider.now(),
      true,
      userService.getCurrentUser()
    );

    postRepository.save(post);
  }

  @Override
  public Post findOne(Long id) {
    return postRepository.findOne(id);
  }

  public Page<Post> findAllByType(String type, int page, int size) {
    PageRequest pageable =
      new PageRequest(page, size, Sort.Direction.DESC, "createdDate");

    switch (type) {
      case FRESH:
        return findAllFreshByPage(pageable);
      case HOT:
        return findAllHotByPage(pageable);
      case TRENDING:
        return findAllTrendingByPage(pageable);
      case REPORTED:
        return findAllReportedByPage(pageable);
      default:
        return findAllFreshByPage(pageable);
    }
  }

  @Override
  public Page<Post> findAllByUser(Long userId, int page, int size) {
    User user = userService.findOne(userId);
    PageRequest pageable = new PageRequest(page, size);
    Page<Post> posts = postRepository.findAllByUser(user, pageable);
    filterPosts(posts);

    return posts;
  }

  private Page<Post> findAllFreshByPage(Pageable pageable) {
    Page<Post> posts = postRepository.findAll(pageable);
    filterPosts(posts);

    return posts;
  }

  private Page<Post> findAllTrendingByPage(Pageable pageable) {
    Page<Post> posts = postRepository.findAllByCommentsCount(pageable);
    filterPosts(posts);

    return posts;
  }

  private Page<Post> findAllHotByPage(Pageable pageable) {
    Page<Post> posts = postRepository.findAllByUpvotePostsCount(pageable);
    filterPosts(posts);

    return posts;
  }

  private Page<Post> findAllReportedByPage(Pageable pageable) {
    Page<Post> posts = postRepository.findAllReportedPosts(pageable);
    filterPosts(posts);

    return posts;
  }

  private boolean filterUpvotedPostsForCurrentUser(List<UpvotePost> posts) {
    return userService.getCurrentUser() != null && posts
      .stream()
      .anyMatch(post -> userService.getCurrentUser().getUsername().equals(post.getUser().getUsername()));
  }

  private boolean filterReportedPostsForCurrentUser(List<ReportPost> posts) {
    return userService.getCurrentUser() != null && posts
      .stream()
      .anyMatch(post -> userService.getCurrentUser().getUsername().equals(post.getUser().getUsername()));
  }

  private void filterPosts(Page<Post> posts) {
    posts.forEach(post -> {
      post.setUpvotedPostByCurrentUser(filterUpvotedPostsForCurrentUser(post.getUpvotePosts()));
      post.setReportedPostByCurrentUser(filterReportedPostsForCurrentUser(post.getReportPosts()));
    });
  }
}
