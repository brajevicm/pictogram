package com.pictogram.pictogram.service;

import com.pictogram.pictogram.util.TimeProviderUtil;
import com.pictogram.pictogram.model.*;
import com.pictogram.pictogram.repository.ActionRepository;
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
  private ActionRepository actionRepository;

  @Autowired
  private PostService postService;

  @Autowired
  private CommentService commentService;

  @Autowired
  private UserService userService;

  @Override
  public void savePost(Long postId) {
    Post post = postService.findOne(postId);
    User user = userService.getCurrentUser();
    ReportPost reportPost = new ReportPost();
    reportPost.setUser(user);
    reportPost.setCreatedDate(TimeProviderUtil.now());
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
    reportComment.setCreatedDate(TimeProviderUtil.now());
    reportComment.setSeen(false);
    reportComment.setComment(comment);

    actionRepository.save(reportComment);
  }
}
