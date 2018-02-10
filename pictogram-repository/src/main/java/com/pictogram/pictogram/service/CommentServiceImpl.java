package com.pictogram.pictogram.service;

import  com.pictogram.pictogram.TimeProvider;
import com.pictogram.pictogram.model.*;
import com.pictogram.pictogram.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  public void save(Comment comment, Long postId) {
    comment.setPost(postService.findOne(postId));
    commentRepository.save(comment);
  }

  @Override
  public void delete(Long commentId) {
    commentRepository.delete(commentId);
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
    List<Comment> comments = pageToCommentsList(commentRepository.findDistinctByPostOrderByUpvoteCommentsDesc(post, pageRequest));
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

  public static List<Comment> pageToCommentsList(Page<Comment> commentPage) {
    List<Comment> comments = new ArrayList<>();
    commentPage.forEach(comments::add);

    return comments;
  }
}
