package com.pictogram.pictogram.dao;

import com.pictogram.pictogram.TimeProvider;
import com.pictogram.pictogram.domain.PostDomain;
import com.pictogram.pictogram.domain.UserDomain;
import com.pictogram.pictogram.model.Post;
import com.pictogram.pictogram.model.ReportPost;
import com.pictogram.pictogram.model.UpvotePost;
import com.pictogram.pictogram.model.User;
import com.pictogram.pictogram.repository.PostRepository;
import com.pictogram.pictogram.storage.StorageService;
import com.pictogram.pictogram.service.CommentService;
import com.pictogram.pictogram.service.PostService;
import com.pictogram.pictogram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  public void save(PostDomain postDto) {
//    String postImage;
//
//    if (postDto.getFile() == null) {
//      postImage = postDto.getPostImage();
//    } else {
//      postImage = storageService.store(postDto.getFile());
//    }

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
