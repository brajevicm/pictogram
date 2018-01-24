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
public class PostDomain {
  @Getter
  @Setter
  private String title, description, postImage;

  @Getter
  @Setter
  private Date createdDate;

  @Getter
  @Setter
  private UserDomain user;

  @Getter
  @Setter
  private List<CommentDomain> comments;

  @Getter
  @Setter
  private List<UpvotePostDomain> upvotePosts;

  @Getter
  @Setter
  private List<ReportPostDomain> reportPosts;

  @Getter
  @Setter
  private boolean enabled, upvotedPostByCurrentUser, reportedPostByCurrentUser;


  public String getUsername() {
    return user.getUsername();
  }

  public Long getUserId() {
    return user.getId();
  }

  public String getUserProfileImage() {
    return user.getProfileImage();
  }

  public int getCommentsCount() {
    return comments.size();
  }

  public int getUpvotesCount() {
    return upvotePosts.size();
  }

  public int getReportsCount() {
    return reportPosts.size();
  }
}
