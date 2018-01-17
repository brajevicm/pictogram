package com.pictogram.pictogram.rest.service;

import com.pictogram.pictogram.rest.model.dto.CommentDto;

/**
 * Project: pictogram
 * Date: 14-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface CommentService {
  void save(CommentDto commentDto);
}
