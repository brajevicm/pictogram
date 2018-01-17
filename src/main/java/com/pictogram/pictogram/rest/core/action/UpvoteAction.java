package com.pictogram.pictogram.rest.core.action;

import com.pictogram.pictogram.rest.core.Action;
import com.pictogram.pictogram.rest.model.Comment;
import com.pictogram.pictogram.rest.model.Post;
import com.pictogram.pictogram.rest.model.Upvote;
import com.pictogram.pictogram.rest.model.User;

/**
 * Project: pictogram
 * Date: 17-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public class UpvoteAction implements Action {
  private Upvote upvote;
  private User user;

  public UpvoteAction(Upvote upvote, User user) {
    this.upvote = upvote;
    this.user = user;
  }

  public void setUpvote(Upvote upvote) {
    this.upvote = upvote;
  }

  @Override
  public void execute() {
    upvote.setUser(user);
    Object obj = upvote.getContent();

//    if (obj instanceof Post) {
//      user.upvotePost((Post) obj);
//    } else if (obj instanceof Comment) {
//      user.upvoteComment((Comment) obj);
//    }
  }
}
