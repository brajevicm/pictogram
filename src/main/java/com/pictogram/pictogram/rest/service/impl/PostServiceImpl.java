package com.pictogram.pictogram.rest.service.impl;

import com.pictogram.pictogram.commons.storage.StorageService;
import com.pictogram.pictogram.commons.utils.TimeProvider;
import com.pictogram.pictogram.rest.model.Post;
import com.pictogram.pictogram.rest.model.User;
import com.pictogram.pictogram.rest.model.dto.PostDto;
import com.pictogram.pictogram.rest.repository.PostRepository;
import com.pictogram.pictogram.rest.service.PostService;
import com.pictogram.pictogram.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

  @Override
  public void save(PostDto postDto) {
    String postImage = storageService.store(postDto.getFile());

    Post post = new Post(
      postDto.getTitle(),
      postDto.getDescription(),
      postImage,
      timeProvider.now(),
      true,
      userService.getCurrentUser(),
      new ArrayList<>()
    );

    postRepository.save(post);
  }

  @Override
  public Post findOne(Long id) {
    return postRepository.findOne(id);
  }
}
