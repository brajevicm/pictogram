package com.pictogram.pictogram.dao;

import com.pictogram.pictogram.TimeProvider;
import com.pictogram.pictogram.domain.PostDomain;
import com.pictogram.pictogram.domain.ReportPostDomain;
import com.pictogram.pictogram.domain.UpvotePostDomain;
import com.pictogram.pictogram.model.Post;
import com.pictogram.pictogram.model.User;
import com.pictogram.pictogram.repository.PostRepository;
import com.pictogram.pictogram.service.CommentService;
import com.pictogram.pictogram.service.PostService;
import com.pictogram.pictogram.service.UserService;
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
@Transactional
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
  public void save(PostDomain postDomain) {
    Post post = toEntityObject(postDomain);

    postRepository.save(post);
  }

  @Override
  public Post findOne(Long id) {
    return postRepository.findOne(id);
  }

  public List<PostDomain> findAllByType(String type, int page, int size) {
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
  public List<PostDomain> findAllByUser(Long userId, int page, int size) {
    User user = UserServiceImpl.toEntityObject(userService.findOne(userId));
    PageRequest pageable = new PageRequest(page, size);
    Page<Post> posts = postRepository.findAllByUser(user, pageable);
    List<PostDomain> postDomains = pageToPostsList(posts);
    filterPosts(postDomains);

    return postDomains;
  }

  private List<PostDomain> findAllFreshByPage(Pageable pageable) {
    Page<Post> posts = postRepository.findAll(pageable);
    List<PostDomain> postDomains = pageToPostsList(posts);
    filterPosts(postDomains);

    return postDomains;
  }

  private List<PostDomain> findAllTrendingByPage(Pageable pageable) {
    Page<Post> posts = postRepository.findAllByCommentsCount(pageable);
    List<PostDomain> postDomains = pageToPostsList(posts);
    filterPosts(postDomains);

    return postDomains;
  }

  private List<PostDomain> findAllHotByPage(Pageable pageable) {
    Page<Post> posts = postRepository.findAllByUpvotePostsCount(pageable);
    List<PostDomain> postDomains = pageToPostsList(posts);
    filterPosts(postDomains);

    return postDomains;
  }

  private List<PostDomain> findAllReportedByPage(Pageable pageable) {
    Page<Post> posts = postRepository.findAllReportedPosts(pageable);
    List<PostDomain> postDomains = pageToPostsList(posts);
    filterPosts(postDomains);

    return postDomains;
  }

  private boolean filterUpvotedPostsForCurrentUser(List<UpvotePostDomain> posts) {
    return userService.getCurrentUser() != null && posts
      .stream()
      .anyMatch(post -> userService.getCurrentUser().getUsername().equals(post.getUser().getUsername()));
  }

  private boolean filterReportedPostsForCurrentUser(List<ReportPostDomain> posts) {
    return userService.getCurrentUser() != null && posts
      .stream()
      .anyMatch(post -> userService.getCurrentUser().getUsername().equals(post.getUser().getUsername()));
  }

  private void filterPosts(List<PostDomain> posts) {
    posts.forEach(post -> {
      post.setUpvotedPostByCurrentUser(filterUpvotedPostsForCurrentUser(post.getUpvotePosts()));
      post.setReportedPostByCurrentUser(filterReportedPostsForCurrentUser(post.getReportPosts()));
    });
  }

  public static List<PostDomain> pageToPostsList(Page<Post> posts) {
    List<PostDomain> postDomains = new ArrayList<>();
    posts.forEach(post -> postDomains.add(post));

    return postDomains;
  }

  public static Post toEntityObject(PostDomain postDomain) {
    Post post = new Post();
    post.setId(postDomain.getId());
    post.setUser(postDomain.getUser());
    post.setCreatedDate(postDomain.getCreatedDate());
    post.setEnabled(postDomain.isEnabled());
    post.setComments(postDomain.getComments());
    post.setDescription(postDomain.getDescription());
    post.setPostImage(postDomain.getPostImage());
    post.setTitle(postDomain.getTitle());
    post.setReportPosts(postDomain.getReportPosts());
    post.setUpvotePosts(postDomain.getUpvotePosts());

    return post;
  }
}
