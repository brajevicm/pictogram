package com.pictogram.pictogram.service;

import com.pictogram.pictogram.model.*;
import com.pictogram.pictogram.repository.CommentRepository;
import com.pictogram.pictogram.util.TimeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
  public Comment save(Comment comment, Long postId) {
    comment.setPost(postService.findOne(postId));
    comment.setUser(userService.getCurrentUser());
    comment.setCreatedDate(timeProvider.now());
    comment.setEnabled(true);
    comment.setReportComments(new ArrayList<>());
    comment.setUpvoteComments(new ArrayList<>());
    comment.setReported(false);
    comment.setUpvoted(false);

    return commentRepository.save(comment);
  }

  @Override
  public Boolean delete(Long commentId) {
    Comment comment = findOne(commentId);
    Boolean isDeleted = Boolean.FALSE;

    if (userService.getCurrentUser().equals(comment.getUser())) {
      commentRepository.delete(commentId);
      isDeleted = Boolean.TRUE;
    }

    return isDeleted;
  }

  @Override
  public Comment findOne(Long commentId) {
    return commentRepository.findOne(commentId);
  }

  @Override
  public List<Comment> findAllByUser(Long userId, int page, int size) {
    User user = userService.findOne(userId);

    PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "createdDate");
    List<Comment> comments = pageToCommentsList(commentRepository.findAllByUser(user, pageRequest));
    filterComments(comments);

    return comments;
  }

  @Override
  public List<Comment> findAllByPost(Long postId, int page, int size) {
    Post post = postService.findOne(postId);

    PageRequest pageRequest = new PageRequest(page, size);
    List<Comment> comments =
      pageToCommentsList(commentRepository.findAllByPostOrderByUpvoteCommentsDesc(post, pageRequest));
    filterComments(comments);

    return comments;
  }

  private boolean filterUpvotedCommentsForCurrentUser(List<UpvoteComment> comments) {
    return userService.getCurrentUser() != null && comments
      .stream()
      .anyMatch(comment -> userService.getCurrentUser().getUsername().equals(comment.getUser().getUsername()));
  }

  private boolean filterReportedCommentsForCurrentUser(List<ReportComment> comments) {
    return userService.getCurrentUser() != null && comments
      .stream()
      .anyMatch(comment -> userService.getCurrentUser().getUsername().equals(comment.getUser().getUsername()));
  }

  private void filterComments(List<Comment> comments) {
    comments.forEach(comment -> {
      comment.setUpvoted(filterUpvotedCommentsForCurrentUser(comment.getUpvoteComments()));
      comment.setReported(filterReportedCommentsForCurrentUser(comment.getReportComments()));
    });
  }

  private List<Comment> pageToCommentsList(Page<Comment> commentPage) {
    return commentPage.getContent();
  }
}
