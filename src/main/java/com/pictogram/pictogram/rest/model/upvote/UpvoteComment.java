package com.pictogram.pictogram.rest.model.upvote;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pictogram.pictogram.rest.model.Comment;
import com.pictogram.pictogram.rest.model.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Project: pictogram
 * Date: 14-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Entity
@Table(name = "upvoted_comments")
public class UpvoteComment extends Upvote {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "comment_id", nullable = false)
  private Comment comment;

  public UpvoteComment() {
  }

  public UpvoteComment(User user, Date date, boolean seen, Comment comment) {
    super(user, date, seen);
    this.comment = comment;
  }

  @JsonIgnore
  public Comment getComment() {
    return comment;
  }

  public void setComment(Comment comment) {
    this.comment = comment;
  }

}
