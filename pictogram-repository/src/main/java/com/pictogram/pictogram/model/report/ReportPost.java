package com.pictogram.pictogram.model.report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pictogram.pictogram.model.Action;
import com.pictogram.pictogram.model.Post;
import com.pictogram.pictogram.model.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Project: pictogram
 * Date: 20-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Entity
@Table(name = "reported_posts", uniqueConstraints =
@UniqueConstraint(columnNames = {"post_id", "user_id"}))
public class ReportPost extends Action {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  public ReportPost() {
  }

  public ReportPost(User user, Date date, boolean seen, Post post) {
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
