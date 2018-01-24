package com.pictogram.pictogram.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pictogram.pictogram.commons.model.AbstractEntity;
import com.pictogram.pictogram.rest.model.report.ReportComment;
import com.pictogram.pictogram.rest.model.upvote.UpvoteComment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

  @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
  private List<UpvoteComment> upvoteComments;

  @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
  private List<ReportComment> reportComments;

  @Transient
  private boolean upvotedCommentByCurrentUser;

  @Transient
  private boolean reportedCommentByCurrentUser;

  public Comment() {
  }

  public Comment(String description, Date createdDate, boolean enabled, User user, Post post) {
    this.description = description;
    this.createdDate = createdDate;
    this.enabled = enabled;
    this.user = user;
    this.post = post;
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

  @JsonIgnore
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @JsonIgnore
  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  @JsonIgnore
  public List<UpvoteComment> getUpvoteComments() {
    return upvoteComments;
  }

  public void setUpvoteComments(List<UpvoteComment> upvoteComments) {
    this.upvoteComments = upvoteComments;
  }

  @JsonIgnore
  public List<ReportComment> getReportComments() {
    return reportComments;
  }

  public void setReportComments(List<ReportComment> reportComments) {
    this.reportComments = reportComments;
  }

  public String getUsername() {
    return user.getUsername();
  }

  public int getUpvotesCount() {
    return upvoteComments.size();
  }

  public int getReportsCount() {
    return reportComments.size();
  }

  public boolean getUpvotedCommentByCurrentUser() {
    return upvotedCommentByCurrentUser;
  }

  public void setUpvotedCommentByCurrentUser(boolean upvotedCommentByCurrentUser) {
    this.upvotedCommentByCurrentUser = upvotedCommentByCurrentUser;
  }

  public void setReportedPostByCurrentUser(boolean reportedCommentByCurrentUser) {
    this.reportedCommentByCurrentUser = reportedCommentByCurrentUser;
  }

  public boolean getReportedCommentByCurrentUser() {
    return reportedCommentByCurrentUser;
  }

  public String getUserProfileImage() {
    return user.getProfileImage();
  }

  public Long getUserId() {
    return user.getId();
  }
}
