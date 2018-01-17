package com.pictogram.pictogram.rest.service.impl;

import com.pictogram.pictogram.commons.utils.TimeProvider;
import com.pictogram.pictogram.rest.model.Comment;
import com.pictogram.pictogram.rest.model.dto.CommentDto;
import com.pictogram.pictogram.rest.repository.CommentRepository;
import com.pictogram.pictogram.rest.service.CommentService;
import com.pictogram.pictogram.rest.service.PostService;
import com.pictogram.pictogram.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Project: pictogram
 * Date: 17-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  CommentRepository commentRepository;

  @Autowired
  TimeProvider timeProvider;

  @Autowired
  UserService userService;

  @Autowired
  PostService postService;

  @Override
  public void save(CommentDto commentDto) {
    Comment comment = new Comment(
      commentDto.getDescription(),
      timeProvider.now(),
      true,
      userService.getCurrentUser(),
      postService.findOne(commentDto.getPostId())
    );

    commentRepository.save(comment);
  }
}
