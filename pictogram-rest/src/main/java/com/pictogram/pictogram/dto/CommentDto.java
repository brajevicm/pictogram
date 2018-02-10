package com.pictogram.pictogram.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Project: pictogram
 * Date: 17-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

  @Getter
  @Setter
  private String description;

  @Getter
  @Setter
  private Long postId;
}
