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
public class UpvoteServiceImpl implements UpvoteService {

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
    UpvotePost upvotePost = new UpvotePost();
    upvotePost.setUser(user);
    upvotePost.setCreatedDate(TimeProviderUtil.now());
    upvotePost.setSeen(false);
    upvotePost.setPost(post);

    actionRepository.save(upvotePost);
  }

  @Override
  public void saveComment(Long commentId) {
    Comment comment = commentService.findOne(commentId);
    User user = userService.getCurrentUser();
    UpvoteComment upvoteComment = new UpvoteComment();
    upvoteComment.setUser(user);
    upvoteComment.setCreatedDate(TimeProviderUtil.now());
    upvoteComment.setSeen(false);
    upvoteComment.setComment(comment);

    actionRepository.save(upvoteComment);
  }

}
