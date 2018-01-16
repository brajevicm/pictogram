package com.pictogram.pictogram.rest.model;

import com.pictogram.pictogram.commons.model.AbstractEntity;
import com.pictogram.pictogram.security.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Entity
@Table(name = "comments")
public class Comment extends AbstractEntity {

  @Column(name = "description", length = 255, unique = true, nullable = false)
  @NotNull
  @Size(min = 2, max = 255)
  private String description;


  @Column(name = "created_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @NotNull
  private Date createdDate;

  @Column(name = "enabled", nullable = false)
  @NotNull
  private boolean enabled;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  public Comment() {
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  @Override
  public String toString() {
    return "Comment{" +
      "description='" + description + '\'' +
      ", createdDate=" + createdDate +
      ", enabled=" + enabled +
      ", user=" + user +
      ", post=" + post +
      '}';
  }
}
