package com.pictogram.pictogram.rest.model.report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pictogram.pictogram.commons.model.AbstractEntity;
import com.pictogram.pictogram.rest.model.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Project: pictogram
 * Date: 20-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@MappedSuperclass
public class Report extends AbstractEntity {

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "created_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  @Column(name = "seen")
  private boolean seen;

  public Report() {
  }

  public Report(User user, Date date, boolean seen) {
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
