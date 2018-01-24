package com.pictogram.pictogram.model.upvote;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pictogram.pictogram.model.Action;
import com.pictogram.pictogram.model.Post;
import com.pictogram.pictogram.model.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Project: pictogram
 * Date: 14-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Entity
@Table(name = "upvoted_posts", uniqueConstraints =
@UniqueConstraint(columnNames = {"post_id", "user_id"}))
public class UpvotePost extends Action {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  public UpvotePost() {
  }

  public UpvotePost(User user, Date date, boolean seen, Post post) {
    super(user, date, seen);
    this.post = post;
  }

  @JsonIgnore
  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }
}
