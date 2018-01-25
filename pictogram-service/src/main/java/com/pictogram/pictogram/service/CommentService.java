package com.pictogram.pictogram.service;

import com.pictogram.pictogram.domain.CommentDomain;

import java.util.List;

/**
 * Project: pictogram
 * Date: 14-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface CommentService {
  void save(CommentDomain comment, Long postId);

  CommentDomain findOne(Long commentId);

  List<CommentDomain> findAllByUser(Long userId, int page, int size);

  List<CommentDomain> findAllByPost(Long postId, int page, int size);
}
