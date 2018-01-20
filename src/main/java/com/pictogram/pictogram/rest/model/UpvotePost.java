package com.pictogram.pictogram.rest.model;

import com.pictogram.pictogram.commons.model.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Project: pictogram
 * Date: 14-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Entity
@Table(name = "upvoted_posts")
public class UpvotePost extends Upvote {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  public UpvotePost() {}

  public UpvotePost(User user, Date date, boolean seen, Post post) {
    super(user, date, seen);
    this.post = post;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }
}
