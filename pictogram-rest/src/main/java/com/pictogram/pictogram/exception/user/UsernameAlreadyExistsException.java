package com.pictogram.pictogram.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Project: pictogram
 * Date: 24-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class UsernameAlreadyExistsException extends RuntimeException {
  private static final long serialVersionUID = -2377047682873747907L;

  public UsernameAlreadyExistsException(String username) {
    super("User with username: " + username + " already exists");
  }
}
