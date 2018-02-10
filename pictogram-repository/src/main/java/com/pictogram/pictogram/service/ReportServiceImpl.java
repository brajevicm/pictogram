package com.pictogram.pictogram.service;

import com.pictogram.pictogram.TimeProvider;
import com.pictogram.pictogram.model.*;
import com.pictogram.pictogram.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Project: pictogram
 * Date: 21-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Service
public class ReportServiceImpl implements ReportService {

  @Autowired
  ActionRepository actionRepository;

  @Autowired
  PostService postService;

  @Autowired
  CommentService commentService;

  @Autowired
  UserService userService;

  @Autowired
  TimeProvider timeProvider;

  @Override
  public void savePost(Long postId) {
    Post post = postService.findOne(postId);
    User user = userService.getCurrentUser();
    ReportPost reportPost = new ReportPost();
    reportPost.setUser(user);
    reportPost.setCreatedDate(timeProvider.now());
    reportPost.setSeen(false);
    reportPost.setPost(post);

    actionRepository.save(reportPost);
  }

  @Override
  public void saveComment(Long commentId) {
    Comment comment = commentService.findOne(commentId);
    User user = userService.getCurrentUser();
    ReportComment reportComment = new ReportComment();
    reportComment.setUser(user);
    reportComment.setCreatedDate(timeProvider.now());
    reportComment.setSeen(false);
    reportComment.setComment(comment);

    actionRepository.save(reportComment);
  }
}
