package com.pictogram.pictogram.service;

import com.pictogram.pictogram.domain.CommentDomain;
import org.springframework.data.domain.Page;

/**
 * Project: pictogram
 * Date: 14-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface CommentService {
  void save(CommentDomain comment, Long postId);

  CommentDomain findOne(Long commentId);

  Page<CommentDomain> findAllByUser(Long userId, int page, int size);

  Page<CommentDomain> findAllByPost(Long postId, int page, int size);
}
