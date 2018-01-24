package com.pictogram.pictogram.dto;

/**
 * Project: pictogram
 * Date: 17-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public class CommentDto {
  private String description;
  private Long postId;

  public CommentDto() {
  }

  public CommentDto(String description, Long postId) {
    this.description = description;
    this.postId = postId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }

  @Override
  public String toString() {
    return "CommentDto{" +
      "description='" + description + '\'' +
      ", postId=" + postId +
      '}';
  }
}
