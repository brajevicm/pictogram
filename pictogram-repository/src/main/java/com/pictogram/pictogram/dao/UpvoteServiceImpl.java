package com.pictogram.pictogram.dao;

import com.pictogram.pictogram.TimeProvider;
import com.pictogram.pictogram.domain.*;
import com.pictogram.pictogram.model.*;
import com.pictogram.pictogram.repository.ActionRepository;
import com.pictogram.pictogram.service.CommentService;
import com.pictogram.pictogram.service.PostService;
import com.pictogram.pictogram.service.UpvoteService;
import com.pictogram.pictogram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Project: pictogram
 * Date: 21-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Service
@Transactional
public class UpvoteServiceImpl implements UpvoteService {

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
    PostDomain post = postService.findOne(postId);
    UserDomain user = userService.getCurrentUser();
    UpvotePostDomain upvotePost = new UpvotePost(user, timeProvider.now(), false, post);

    actionRepository.save(upvotePost);
  }

  @Override
  public void saveComment(Long commentId) {
    CommentDomain comment = commentService.findOne(commentId);
    UserDomain user = userService.getCurrentUser();
    UpvoteCommentDomain upvoteComment = new UpvoteComment(user, timeProvider.now(), false, comment);

    actionRepository.save(upvoteComment);
  }
}
