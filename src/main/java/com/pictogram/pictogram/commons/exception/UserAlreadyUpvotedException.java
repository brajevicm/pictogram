package com.pictogram.pictogram.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Project: pictogram
 * Date: 24-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class UserAlreadyUpvotedException extends RuntimeException {
  private static final long serialVersionUID = -2406890228491689207L;

  public UserAlreadyUpvotedException(String message) {
    super(message);
  }
}
