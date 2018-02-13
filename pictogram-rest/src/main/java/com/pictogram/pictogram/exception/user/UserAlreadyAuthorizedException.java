package com.pictogram.pictogram.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Project: com.pictogram.pictogram.exception.user
 * Date: 13.2.18.
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class UserAlreadyAuthorizedException extends RuntimeException {
  public UserAlreadyAuthorizedException() {
    super("User already authorized.");
  }
}
