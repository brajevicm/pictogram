package com.pictogram.pictogram.exception.comment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Project: com.pictogram.pictogram.exception.comment
 * Date: 12.2.18.
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CommentsNotFoundException extends RuntimeException {
  public CommentsNotFoundException(String entity) {
    super(entity + " doesn't have any comments.");
  }
}
