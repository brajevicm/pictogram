package com.pictogram.pictogram.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Project: pictogram
 * Date: 24-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class UserAlreadyReportedException extends RuntimeException {
  private static final long serialVersionUID = 4686892369957460901L;

  public UserAlreadyReportedException(String message) {
    super(message);
  }
}
