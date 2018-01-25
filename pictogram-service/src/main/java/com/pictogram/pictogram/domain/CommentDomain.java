package com.pictogram.pictogram.domain;

import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * Project: pictogram
 * Date: 24-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentDomain {
  @Getter
  @Setter
  private Long id;

  @Getter
  @Setter
  private String description;

  @Getter
  @Setter
  private Date createdDate;

  @Getter
  @Setter
  private boolean enabled;

  @Getter
  @Setter
  private UserDomain user;

  @Getter
  @Setter
  private PostDomain post;

  @Getter
  @Setter
  private List<UpvoteCommentDomain> upvoteComments;

  @Getter
  @Setter
  private List<ReportCommentDomain> reportComments;

  @Getter
  @Setter
  private boolean upvotedCommentByCurrentUser, reportedCommentByCurrentUser;


  public CommentDomain(String description, Date createdDate, boolean enabled, UserDomain user, PostDomain post) {
    this.description = description;
    this.createdDate = createdDate;
    this.enabled = enabled;
    this.user = user;
    this.post = post;
  }

  public int getUpvotesCount() {
    return upvoteComments.size();
  }

  public int getReportsCount() {
    return reportComments.size();
  }

  public String getUserProfileImage() {
    return user.getProfileImage();
  }

  public String getUsername() {
    return user.getUsername();
  }

  public Long getUserId() {
    return user.getId();
  }
}
