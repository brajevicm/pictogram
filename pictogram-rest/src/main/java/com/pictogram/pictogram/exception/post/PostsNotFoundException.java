package com.pictogram.pictogram.exception.post;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Project: com.pictogram.pictogram.exception.post
 * Date: 13.2.18.
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PostsNotFoundException extends RuntimeException {
  public PostsNotFoundException() {
    super("Posts not found.");
  }
}
