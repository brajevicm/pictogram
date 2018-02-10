package com.pictogram.pictogram.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * Project: pictogram
 * Date: 17-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@NoArgsConstructor
public class PostDto {

  @Getter
  @Setter
  private String title, postImage, description;

  @Getter
  @Setter
  private MultipartFile file;

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

}
