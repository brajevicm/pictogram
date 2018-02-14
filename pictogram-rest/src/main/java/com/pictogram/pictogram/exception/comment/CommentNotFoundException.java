package com.pictogram.pictogram.exception.comment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Project: pictogram
 * Date: 24-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CommentNotFoundException extends RuntimeException {
  private static final long serialVersionUID = -4535970573754528705L;

  public CommentNotFoundException(Long commentId) {
    super("Could not find Comment with id " + commentId);
  }
}
