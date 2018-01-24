package com.pictogram.pictogram.dto;

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
  private String postImage;

  public PostDto() {
  }

  public PostDto(String title, String description, String postImage) {
    this.title = title;
    this.description = description;
    this.postImage = postImage;
  }

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

  public void setFile(MultipartFile file) {
    this.file = file;
  }

  public void setPostImage(MultipartFile file) {
    this.file = file;
  }

  public String getPostImage() {
    return postImage;
  }

  public void setPostImage(String postImage) {
    this.postImage = postImage;
  }
}
