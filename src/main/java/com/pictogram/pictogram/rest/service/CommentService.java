package com.pictogram.pictogram.rest.service;

import com.pictogram.pictogram.rest.model.Comment;
import com.pictogram.pictogram.rest.model.dto.CommentDto;
import org.springframework.data.domain.Page;

/**
 * Project: pictogram
 * Date: 14-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface CommentService {
  void save(CommentDto commentDto, Long postId);

  Page<Comment> findAllByUser(Long postId, int page, int size);
}
