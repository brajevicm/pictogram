package com.pictogram.pictogram.dao;

import com.pictogram.pictogram.TimeProvider;
import com.pictogram.pictogram.domain.*;
import com.pictogram.pictogram.model.Comment;
import com.pictogram.pictogram.model.Post;
import com.pictogram.pictogram.model.User;
import com.pictogram.pictogram.repository.CommentRepository;
import com.pictogram.pictogram.service.CommentService;
import com.pictogram.pictogram.service.PostService;
import com.pictogram.pictogram.service.UserService;
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
@Transactional
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
  public void save(CommentDomain commentDomain, Long postId) {
    Comment comment = toEntityObject(commentDomain);

    commentRepository.save(comment);
  }

  @Override
  public CommentDomain findOne(Long commentId) {
    return commentRepository.findOne(commentId);
  }

  @Override
  public List<CommentDomain> findAllByUser(Long userId, int page, int size) {
    User user = UserServiceImpl.toEntityObject(userService.findOne(userId));

    PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "createdDate");
    Page<Comment> comments = commentRepository.findAllByUser(user, pageRequest);
    List<CommentDomain> commentDomains = pageToCommentsList(comments);
    filterComments(commentDomains);

    return commentDomains;
  }

  @Override
  public List<CommentDomain> findAllByPost(Long postId, int page, int size) {
    Post post = PostServiceImpl.toEntityObject(postService.findOne(postId));

    PageRequest pageRequest = new PageRequest(page, size);
    Page<Comment> comments = commentRepository.findDistinctByPostOrderByUpvoteCommentsDesc(post, pageRequest);
    List<CommentDomain> commentDomains = pageToCommentsList(comments);
    filterComments(commentDomains);

    return commentDomains;
  }

  private boolean filterUpvotedCommentsForCurrentUser(List<UpvoteCommentDomain> comments) {
    return userService.getCurrentUser() != null && comments
      .stream()
      .anyMatch(comment -> userService.getCurrentUser().getUsername().equals(comment.getUser().getUsername()));
  }

  private boolean filterReportedCommentsForCurrentUser(List<ReportCommentDomain> comments) {
    return userService.getCurrentUser() != null && comments
      .stream()
      .anyMatch(comment -> userService.getCurrentUser().getUsername().equals(comment.getUser().getUsername()));
  }

  private void filterComments(List<CommentDomain> comments) {
    comments.forEach(comment -> {
      comment.setUpvotedCommentByCurrentUser(filterUpvotedCommentsForCurrentUser(comment.getUpvoteComments()));
      comment.setReportedCommentByCurrentUser(filterReportedCommentsForCurrentUser(comment.getReportComments()));
    });
  }

  public static List<CommentDomain> pageToCommentsList(Page<Comment> comments) {
    List<CommentDomain> commentDomains = new ArrayList<>();
    comments.forEach(post -> commentDomains.add(post));

    return commentDomains;
  }

  public static Comment toEntityObject(CommentDomain commentDomain) {
    Comment comment = new Comment();
    comment.setId(commentDomain.getId());
    comment.setUser(commentDomain.getUser());
    comment.setCreatedDate(commentDomain.getCreatedDate());
    comment.setEnabled(commentDomain.isEnabled());
    comment.setDescription(commentDomain.getDescription());
    comment.setPost(commentDomain.getPost());
    comment.setReportComments(commentDomain.getReportComments());
    comment.setUpvoteComments(commentDomain.getUpvoteComments());

    return comment;
  }
}
