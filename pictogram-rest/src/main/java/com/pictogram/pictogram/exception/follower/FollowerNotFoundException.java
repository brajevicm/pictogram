package com.pictogram.pictogram.exception.follower;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Project: com.pictogram.pictogram.exception.follower
 * Date: 12.2.18.
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FollowerNotFoundException extends RuntimeException {
  public FollowerNotFoundException() {
    super("Follower not found.");
  }
}
