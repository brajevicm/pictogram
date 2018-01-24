package com.pictogram.pictogram.model;

import com.pictogram.pictogram.domain.CommentDomain;
import com.pictogram.pictogram.domain.UpvoteCommentDomain;
import com.pictogram.pictogram.domain.UserDomain;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
public class UpvoteComment extends UpvoteCommentDomain {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Override
  public Long getId() {
    return super.getId();
  }

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  @Override
  public UserDomain getUser() {
    return super.getUser();
  }

  @Column(name = "created_date")
  @Temporal(TemporalType.TIMESTAMP)
  @Override
  public Date getDate() {
    return super.getDate();
  }

  @Column(name = "seen")
  @Override
  public boolean isSeen() {
    return super.isSeen();
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "comment_id", nullable = false)
  @Override
  public CommentDomain getComment() {
    return super.getComment();
  }
}
