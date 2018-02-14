package com.pictogram.pictogram.service;

import com.pictogram.pictogram.model.Comment;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Project: pictogram
 * Date: 14-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface CommentService {
  Comment save(Comment comment, Long postId);

  Boolean delete(Comment comment);

  Comment findOne(Long commentId);

  List<Comment> findAllByUser(Long userId, Pageable pageable);

  List<Comment> findAllByPost(Long postId, Pageable pageable);
}
