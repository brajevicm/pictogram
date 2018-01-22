package com.pictogram.pictogram.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pictogram.pictogram.commons.model.AbstractEntity;
import com.pictogram.pictogram.rest.model.report.ReportPost;
import com.pictogram.pictogram.rest.model.upvote.UpvotePost;

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
@Table(name = "posts")
public class Post extends AbstractEntity {

  @Column(name = "title", length = 32, unique = true, nullable = false)
  @NotNull
  @Size(min = 2, max = 32)
  private String title;

  @Column(name = "description", length = 255, unique = true, nullable = false)
  @NotNull
  @Size(min = 2, max = 255)
  private String description;

  @Column(name = "post_image", nullable = false)
  @NotNull
  private String postImage;

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

  @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
  private List<Comment> comments;

  @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
  private List<UpvotePost> upvotePosts;

  @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
  private List<ReportPost> reportPosts;

  @Transient
  private boolean upvotedPostByCurrentUser;

  @Transient
  private boolean reportedPostByCurrentUser;

  public Post() {

  }

  public Post(String title, String description, String postImage, Date createdDate, boolean enabled, User user) {
    this.title = title;
    this.description = description;
    this.postImage = postImage;
    this.createdDate = createdDate;
    this.enabled = enabled;
    this.user = user;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPostImage() {
    return postImage;
  }

  public void setPostImage(String postImage) {
    this.postImage = postImage;
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

  public String getUsername() {
    return user.getUsername();
  }

  @JsonIgnore
  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  @JsonIgnore
  public List<UpvotePost> getUpvotePosts() {
    return upvotePosts;
  }

  public void setUpvotePosts(List<UpvotePost> upvotePosts) {
    this.upvotePosts = upvotePosts;
  }

  @JsonIgnore
  public List<ReportPost> getReportPosts() {
    return reportPosts;
  }

  public void setReportPosts(List<ReportPost> reportPosts) {
    this.reportPosts = reportPosts;
  }

  public int getCommentsCount() {
    return comments.size();
  }

  public int getUpvotesCount() {
    return upvotePosts.size();
  }

  public void setUpvotedPostByCurrentUser(boolean upvotedPostByCurrentUser) {
    this.upvotedPostByCurrentUser = upvotedPostByCurrentUser;
  }

  public boolean getUpvotedPostByCurrentUser() {
    return upvotedPostByCurrentUser;
  }

  public void setReportedPostByCurrentUser(boolean reportedPostByCurrentUser) {
    this.reportedPostByCurrentUser = reportedPostByCurrentUser;
  }

  public boolean getReportedPostByCurrentUser() {
    return reportedPostByCurrentUser;
  }
}


