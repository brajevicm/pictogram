package com.pictogram.pictogram.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pictogram.pictogram.commons.model.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

  @OneToMany(
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    mappedBy = "post")
  private List<Comment> comments = new ArrayList<>();

  public Post() {

  }


  public Post(String title, String description, String postImage, Date createdDate, boolean enabled, User user, List<Comment> comments) {
    this.title = title;
    this.description = description;
    this.postImage = postImage;
    this.createdDate = createdDate;
    this.enabled = enabled;
    this.user = user;
    this.comments = comments;
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

  @JsonIgnore
  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public void addComment(Comment comment) {
    comments.add(comment);
    comment.setPost(this);
  }

  public void removeComment(Comment comment) {
    comments.remove(comment);
    comment.setPost(null);
  }

  @Override
  public String toString() {
    return "Post{" +
            "title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", postImage='" + postImage + '\'' +
            ", createdDate=" + createdDate +
            ", enabled=" + enabled +
            ", user=" + user +
            ", comments=" + comments +
            '}';
  }

  public String getUsername() {
    return user.getUsername();
  }


}


