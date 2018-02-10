package com.pictogram.pictogram.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Getter
  @Setter
  private Long id;

  @Column(name = "title", length = 32, nullable = false)
  @Getter
  @Setter
  private String title;

  @Column(name = "description", nullable = false)
  @Getter
  @Setter
  private String description;

  @Column(name = "post_image", nullable = false)
  @Getter
  @Setter
  private String postImage;

  @Column(name = "created_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @Getter
  @Setter
  private Date createdDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  @Getter(onMethod = @__(@JsonIgnore))
  @Setter
  private User user;

  @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
  @Getter(onMethod = @__(@JsonIgnore))
  @Setter
  private List<Comment> comments;

  @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
  @Getter(onMethod = @__(@JsonIgnore))
  @Setter
  private List<UpvotePost> upvotePosts;

  @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
  @Getter(onMethod = @__(@JsonIgnore))
  @Setter
  private List<ReportPost> reportPosts;

  @Column(name = "enabled", nullable = false)
  @Getter(onMethod = @__(@JsonIgnore))
  @Setter
  private boolean enabled;

  @Transient
  @Getter
  @Setter
  private boolean upvoted;

  @Transient
  @Getter
  @Setter
  private boolean reported;

  public int getUpvotesCount() {
    return upvotePosts.size();
  }

  public int getReportsCount() {
    return reportPosts.size();
  }

  public int getCommentsCount() {
    return comments.size();
  }

  public String getUsername() {
    return user.getUsername();
  }

  public Long getUserId() {
    return user.getId();
  }

  public String getUserProfileImage() {
    return user.getProfileImage();
  }
}