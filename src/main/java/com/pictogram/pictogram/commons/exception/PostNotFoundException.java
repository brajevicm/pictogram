package com.pictogram.pictogram.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Project: pictogram
 * Date: 24-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 502673979845995652L;

  public PostNotFoundException(Long postId) {
    super("Could not find post with id " + postId);
  }
}
