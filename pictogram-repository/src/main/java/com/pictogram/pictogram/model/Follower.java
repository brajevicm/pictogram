package com.pictogram.pictogram.model;

import com.pictogram.pictogram.domain.FollowerDomain;
import com.pictogram.pictogram.domain.UserDomain;

import javax.persistence.*;

/**
 * Project: pictogram
 * Date: 15-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Entity
@Table(name = "followers")
public class Follower extends FollowerDomain {
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "follow_id", nullable = false)
  @Override
  public UserDomain getFollowing() {
    return super.getFollowing();
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  @Override
  public UserDomain getUser() {
    return super.getUser();
  }
}
