package com.pictogram.pictogram.dao;

import com.pictogram.pictogram.TimeProvider;
import com.pictogram.pictogram.repository.CommentRepository;
import com.pictogram.pictogram.service.CommentService;
import com.pictogram.pictogram.service.PostService;
import com.pictogram.pictogram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
    if (user == null) {
      throw new UserNotFoundException(userId);
    }

    PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "createdDate");
    Page<Comment> comments = commentRepository.findAllByUser(user, pageRequest);
    filterComments(comments);

    return comments;
  }

  @Override
  public Page<Comment> findAllByPost(Long postId, int page, int size) {
    Post post = postService.findOne(postId);
    if (post == null) {
      throw new PostNotFoundException(postId);
    }

    PageRequest pageRequest = new PageRequest(page, size);
    Page<Comment> comments = commentRepository.findDistinctByPostOrderByUpvoteCommentsDesc(post, pageRequest);
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

  private void filterComments(Page<Comment> comments) {
    comments.forEach(comment -> {
      comment.setUpvotedCommentByCurrentUser(filterUpvotedCommentsForCurrentUser(comment.getUpvoteComments()));
      comment.setReportedPostByCurrentUser(filterReportedCommentsForCurrentUser(comment.getReportComments()));
    });
  }
}
