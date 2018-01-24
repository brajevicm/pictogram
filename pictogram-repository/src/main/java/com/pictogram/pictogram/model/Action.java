package com.pictogram.pictogram.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * Project: pictogram
 * Date: 20-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@MappedSuperclass
public class Action extends AbstractEntity {

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "created_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  @Column(name = "seen")
  private boolean seen;

  public Action() {
  }

  public Action(User user, Date date, boolean seen) {
    this.user = user;
    this.date = date;
    this.seen = seen;
  }

  @JsonIgnore
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public boolean isSeen() {
    return seen;
  }

  public void setSeen(boolean seen) {
    this.seen = seen;
  }
}
