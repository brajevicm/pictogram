package com.pictogram.pictogram.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Project: com.pictogram.pictogram.exception
 * Date: 12.2.18.
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UserNotAuthorizedException extends RuntimeException {

  public UserNotAuthorizedException() {
    super("Unauthorized");
  }
}
