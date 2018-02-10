package com.pictogram.pictogram.service;

import  com.pictogram.pictogram.TimeProvider;
import com.pictogram.pictogram.model.Post;
import com.pictogram.pictogram.model.ReportPost;
import com.pictogram.pictogram.model.UpvotePost;
import com.pictogram.pictogram.model.User;
import com.pictogram.pictogram.repository.PostRepository;
import com.pictogram.pictogram.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
  UserService userService;

  @Autowired
  CommentService commentService;

  @Override
  public void save(Post post) {
    postRepository.save(post);
  }

  @Override
  public Post findOne(Long id) {
    return postRepository.findOne(id);
  }

  public List<Post> findAllByType(String type, int page, int size) {
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
  public List<Post> findAllByUser(Long userId, int page, int size) {
    User user = userService.findOne(userId);
    PageRequest pageable = new PageRequest(page, size);
    List<Post> posts = pageToPostsList(postRepository.findAllByUser(user, pageable));
    filterPosts(posts);

    return posts;
  }

  private List<Post> findAllFreshByPage(Pageable pageable) {
    List<Post> posts = pageToPostsList(postRepository.findAll(pageable));
    filterPosts(posts);

    return posts;
  }

  private List<Post> findAllTrendingByPage(Pageable pageable) {
    List<Post> posts = pageToPostsList(postRepository.findAllByCommentsCount(pageable));
    filterPosts(posts);

    return posts;
  }

  private List<Post> findAllHotByPage(Pageable pageable) {
    List<Post> posts = pageToPostsList(postRepository.findAllByUpvotePostsCount(pageable));
    filterPosts(posts);

    return posts;
  }

  private List<Post> findAllReportedByPage(Pageable pageable) {
    List<Post> posts = pageToPostsList(postRepository.findAllReportedPosts(pageable));
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

  private void filterPosts(List<Post> posts) {
    posts.forEach(post -> {
      post.setUpvoted(filterUpvotedPostsForCurrentUser(post.getUpvotePosts()));
      post.setReported(filterReportedPostsForCurrentUser(post.getReportPosts()));
    });
  }

  public static List<Post> pageToPostsList(Page<Post> postPage) {
    List<Post> posts = new ArrayList<>();
    postPage.forEach(posts::add);

    return posts;
  }
}
