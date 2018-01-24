package com.pictogram.pictogram.model;

import com.pictogram.pictogram.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class Post extends PostDomain {
  @Column(name = "title", length = 32, unique = true, nullable = false)
  @Override
  public String getTitle() {
    return super.getTitle();
  }

  @Column(name = "description", length = 255, unique = true, nullable = false)
  @Override
  public String getDescription() {
    return super.getDescription();
  }

  @Column(name = "post_image", nullable = false)
  @Override
  public String getPostImage() {
    return super.getPostImage();
  }

  @Column(name = "created_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @Override
  public Date getCreatedDate() {
    return super.getCreatedDate();
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  @Override
  public UserDomain getUser() {
    return super.getUser();
  }

  @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
  @Override
  public List<CommentDomain> getComments() {
    return super.getComments();
  }

  @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
  @Override
  public List<UpvotePostDomain> getUpvotePosts() {
    return super.getUpvotePosts();
  }

  @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
  @Override
  public List<ReportPostDomain> getReportPosts() {
    return super.getReportPosts();
  }

  @Column(name = "enabled", nullable = false)
  @Override
  public boolean isEnabled() {
    return super.isEnabled();
  }
}


