package com.pictogram.pictogram.rest.core.action;

import com.pictogram.pictogram.rest.core.Action;
import com.pictogram.pictogram.rest.model.Comment;
import com.pictogram.pictogram.rest.model.Post;
import com.pictogram.pictogram.rest.model.User;

/**
 * Project: pictogram
 * Date: 17-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public class CommentAction implements Action {
  private Comment comment;
  private User user;
  private Post post;

  public CommentAction(Comment comment, User user, Post post) {
    this.comment = comment;
    this.user = user;
    this.post = post;
  }

  @Override
  public void execute() {
    comment.setUser(user);
    comment.setPost(post);
    user.addComment(comment);
  }
}
