package com.pictogram.pictogram.service;

import com.pictogram.pictogram.model.Comment;

import java.util.List;

/**
 * Project: pictogram
 * Date: 14-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface CommentService {
  Comment save(Comment comment, Long postId);

  Boolean delete(Long commentId);

  Comment findOne(Long commentId);

  List<Comment> findAllByUser(Long userId, int page, int size);

  List<Comment> findAllByPost(Long postId, int page, int size);
}
