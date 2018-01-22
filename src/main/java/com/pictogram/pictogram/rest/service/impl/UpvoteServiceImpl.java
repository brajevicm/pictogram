package com.pictogram.pictogram.rest.service.impl;

import com.pictogram.pictogram.commons.utils.TimeProvider;
import com.pictogram.pictogram.rest.model.Comment;
import com.pictogram.pictogram.rest.model.Post;
import com.pictogram.pictogram.rest.model.User;
import com.pictogram.pictogram.rest.model.upvote.UpvoteComment;
import com.pictogram.pictogram.rest.model.upvote.UpvotePost;
import com.pictogram.pictogram.rest.repository.ActionRepository;
import com.pictogram.pictogram.rest.service.CommentService;
import com.pictogram.pictogram.rest.service.PostService;
import com.pictogram.pictogram.rest.service.UpvoteService;
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
    Post post = postService.findOne(postId);
    User user = userService.getCurrentUser();
    UpvotePost upvotePost = new UpvotePost(user, timeProvider.now(), false, post);

    actionRepository.save(upvotePost);
  }

  @Override
  public void saveComment(Long commentId) {
    Comment comment = commentService.findOne(commentId);
    User user = userService.getCurrentUser();
    UpvoteComment upvoteComment = new UpvoteComment(user, timeProvider.now(), false, comment);

    actionRepository.save(upvoteComment);
  }
}
