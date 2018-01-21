package com.pictogram.pictogram.rest.service.impl;

import com.pictogram.pictogram.commons.utils.TimeProvider;
import com.pictogram.pictogram.rest.model.Comment;
import com.pictogram.pictogram.rest.model.Post;
import com.pictogram.pictogram.rest.model.User;
import com.pictogram.pictogram.rest.model.report.ReportComment;
import com.pictogram.pictogram.rest.model.report.ReportPost;
import com.pictogram.pictogram.rest.repository.ReportRepository;
import com.pictogram.pictogram.rest.service.CommentService;
import com.pictogram.pictogram.rest.service.PostService;
import com.pictogram.pictogram.rest.service.ReportService;
import com.pictogram.pictogram.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Project: pictogram
 * Date: 21-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Service
public class ReportServiceImpl implements ReportService {

  @Autowired
  ReportRepository reportRepository;

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
    ReportPost reportPost = new ReportPost(user, timeProvider.now(), false, post);

    reportRepository.save(reportPost);
  }

  @Override
  public void saveComment(Long commentId) {
    Comment comment = commentService.findOne(commentId);
    User user = userService.getCurrentUser();
    ReportComment reportComment = new ReportComment(user, timeProvider.now(), false, comment);

    reportRepository.save(reportComment);
  }
}
