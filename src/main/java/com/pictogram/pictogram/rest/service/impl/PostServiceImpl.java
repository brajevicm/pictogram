package com.pictogram.pictogram.rest.service.impl;

import com.pictogram.pictogram.commons.storage.StorageService;
import com.pictogram.pictogram.commons.utils.TimeProvider;
import com.pictogram.pictogram.rest.model.Comment;
import com.pictogram.pictogram.rest.model.Post;
import com.pictogram.pictogram.rest.model.User;
import com.pictogram.pictogram.rest.model.dto.PostDto;
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
    String postImage = storageService.store(postDto.getFile());

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
      new PageRequest(page, size, Sort.Direction.ASC, "createdDate");

    switch (type) {
      case "fresh":
        return findAllFreshByPage(pageable);
      case "hot":
        return findAllHotByPage(pageable);
      case "trending":
        return findAllTrendingByPage(pageable);
      default:
        return findAllFreshByPage(pageable);
    }
  }

  @Override
  public Page<Post> findAllByUser(Long userId, int page, int size) {
    User user = userService.findOne(userId);
    PageRequest pageable =
      new PageRequest(page, size, Sort.Direction.ASC, "createdDate");

    return postRepository.findAllByUser(user, pageable);
  }

  private Page<Post> findAllFreshByPage(Pageable pageable) {
    return postRepository.findAll(pageable);
  }

  private Page<Post> findAllTrendingByPage(Pageable pageable) {
    return postRepository.findTopOrderByComments(pageable);
  }

  private Page<Post> findAllHotByPage(Pageable pageable) {
    return postRepository.findAll(pageable);
  }

}
