package com.pictogram.pictogram.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Project: pictogram
 * Date: 24-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1141146571932472669L;

  public UserNotFoundException(Long userId) {
    super("Could not find User with id " + userId);
  }
}
