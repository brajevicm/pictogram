package com.pictogram.pictogram.model;

import com.pictogram.pictogram.domain.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Entity
@Table(name = "comments")
public class Comment extends CommentDomain {
  @Column(name = "description", unique = true, nullable = false)
  @Override
  public String getDescription() {
    return super.getDescription();
  }

  @Column(name = "created_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @Override
  public Date getCreatedDate() {
    return super.getCreatedDate();
  }

  @Column(name = "enabled", nullable = false)
  @Override
  public boolean isEnabled() {
    return super.isEnabled();
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  @Override
  public UserDomain getUser() {
    return super.getUser();
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  @Override
  public PostDomain getPost() {
    return super.getPost();
  }

  @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
  @Override
  public List<UpvoteCommentDomain> getUpvoteComments() {
    return super.getUpvoteComments();
  }

  @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
  @Override
  public List<ReportCommentDomain> getReportComments() {
    return super.getReportComments();
  }

}
