package com.pictogram.pictogram.service;

import com.pictogram.pictogram.model.*;
import com.pictogram.pictogram.repository.PostRepository;
import com.pictogram.pictogram.util.TimeProviderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
  private PostRepository postRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private FollowerService followerService;

  @Override
  public Boolean delete(Post post) {
    Boolean isDeleted = Boolean.FALSE;

    if (userService.getCurrentUser().equals(post.getUser())) {
      postRepository.delete(post.getId());
      isDeleted = Boolean.TRUE;
    }

    return isDeleted;
  }

  @Override
  public Post save(Post post) {
    post.setEnabled(true);
    post.setCreatedDate(TimeProviderUtil.now());
    post.setComments(new ArrayList<>());
    post.setUpvotePosts(new ArrayList<>());
    post.setReportPosts(new ArrayList<>());
    post.setReported(false);
    post.setUpvoted(false);

    return postRepository.save(post);
  }

  @Override
  public Post findOne(Long id) {
    return postRepository.findOne(id);
  }

  public List<Post> findAllByType(String type, Pageable pageable) {
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
  public List<Post> findAllByUser(User user, Pageable pageable) {
    List<Post> posts = pageToPostsList(postRepository.findAllByUser(user, pageable));
    filterPosts(posts);

    return posts;
  }

  @Override
  public List<Post> findAllByFollows(User user, Pageable pageable) {
    List<Follower> followers = followerService.findAllByUser(user);
    List<User> follows = new ArrayList<>();
    followers.forEach(follower -> follows.add(follower.getFollow()));

    List<Post> posts = pageToPostsList(postRepository.findAllByUserIn(follows, pageable));
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

  private List<Post> pageToPostsList(Page<Post> postPage) {
    return postPage.getContent();
  }
}
