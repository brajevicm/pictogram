package com.pictogram.pictogram.rest.core.action;

import com.pictogram.pictogram.rest.core.Action;
import com.pictogram.pictogram.rest.model.Post;
import com.pictogram.pictogram.rest.model.User;

/**
 * Project: pictogram
 * Date: 17-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public class PostAction implements Action {
  private Post post;
  private User user;

  public PostAction(Post post, User user) {
    this.post = post;
    this.user = user;
  }

  @Override
  public void execute() {
    post.setUser(user);
    user.addPost(post);
  }
}
