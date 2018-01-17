package com.pictogram.pictogram.security.model.jwt;

import java.io.Serializable;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public class AuthenticationRequest implements Serializable {

  private static final long serialVersionUID = 7281369319522063373L;

  private String username;
  private String password;

  public AuthenticationRequest() {
    super();
  }

  public AuthenticationRequest(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
