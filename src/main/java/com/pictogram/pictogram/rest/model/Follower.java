package com.pictogram.pictogram.rest.model;

import com.pictogram.pictogram.commons.model.AbstractEntity;

import javax.persistence.*;

/**
 * Project: pictogram
 * Date: 15-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Entity
@Table(name = "followers")
public class Follower extends AbstractEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "follow_id", nullable = false)
  private User following;

  public Follower() {
  }

  public Follower(User user, User following) {
    this.user = user;
    this.following = following;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public User getFollowing() {
    return following;
  }

  public void setFollowing(User following) {
    this.following = following;
  }
}
