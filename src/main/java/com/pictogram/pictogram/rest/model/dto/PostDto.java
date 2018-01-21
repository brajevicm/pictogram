package com.pictogram.pictogram.rest.model.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * Project: pictogram
 * Date: 17-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public class PostDto {
  private String title;
  private String description;
  private MultipartFile file;

  public PostDto(String title, String description, MultipartFile file) {
    this.title = title;
    this.description = description;
    this.file = file;
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

  public MultipartFile getFile() {
    return file;
  }

  public void setPostImage(MultipartFile file) {
    this.file = file;
  }

  @Override
  public String toString() {
    return "PostDto{" +
      "title='" + title + '\'' +
      ", description='" + description + '\'' +
      ", postImage='" + file + '\'' +
      '}';
  }
}
