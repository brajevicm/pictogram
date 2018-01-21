package com.pictogram.pictogram.rest.service.impl;

import com.pictogram.pictogram.commons.utils.TimeProvider;
import com.pictogram.pictogram.rest.model.Comment;
import com.pictogram.pictogram.rest.model.Post;
import com.pictogram.pictogram.rest.model.User;
import com.pictogram.pictogram.rest.model.dto.CommentDto;
import com.pictogram.pictogram.rest.repository.CommentRepository;
import com.pictogram.pictogram.rest.service.CommentService;
import com.pictogram.pictogram.rest.service.PostService;
import com.pictogram.pictogram.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
  public void save(CommentDto commentDto, Long postId) {
    Comment comment = new Comment(
      commentDto.getDescription(),
      timeProvider.now(),
      true,
      userService.getCurrentUser(),
      postService.findOne(postId)
    );

    commentRepository.save(comment);
  }

  @Override
  public Comment findOne(Long commentId) {
    return commentRepository.findOne(commentId);
  }

  @Override
  public Page<Comment> findAllByUser(Long userId, int page, int size) {
    User user = userService.findOne(userId);
    PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "createdDate");

    return commentRepository.findAllByUser(user, pageRequest);
  }

  @Override
  public Page<Comment> findAllByPost(Long postId, int page, int size) {
    Post post = postService.findOne(postId);
    PageRequest pageRequest = new PageRequest(page, size);

    return commentRepository.findAllByPostOrderByUpvoteCommentsDesc(post, pageRequest);
  }
}
