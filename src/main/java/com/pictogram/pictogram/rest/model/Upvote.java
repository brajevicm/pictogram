package com.pictogram.pictogram.rest.model;

/**
 * Project: pictogram
 * Date: 14-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public class Upvote<T> {
  private T content;
  private User user;

  public Upvote(T content) {
    this.content = content;
  }

  public void setContent(T content) {
    this.content = content;
  }

  public T getContent() {
    return content;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
