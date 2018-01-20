package com.pictogram.pictogram.rest.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Project: pictogram
 * Date: 20-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Entity
@Table(name = "reported_comments")
public class ReportComment extends Report {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "comment_id", nullable = false)
  private Comment comment;

  public ReportComment() {
  }

  public ReportComment(User user, Date date, boolean seen, Comment comment) {
    super(user, date, seen);
    this.comment = comment;
  }

  public Comment getComment() {
    return comment;
  }

  public void setComment(Comment comment) {
    this.comment = comment;
  }
}
