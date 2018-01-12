package com.pictogram.pictogram.security;

import java.io.Serializable;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public class JwtAuthenticationResponse implements Serializable {

  private static final long serialVersionUID = -5513905382221227821L;

  private final String token;

  public JwtAuthenticationResponse(String token) {
    this.token = token;
  }

  public String getToken() {
    return this.token;
  }
}
