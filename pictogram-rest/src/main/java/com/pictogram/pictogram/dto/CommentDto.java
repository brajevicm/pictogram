package com.pictogram.pictogram.dto;

import com.pictogram.pictogram.model.Comment;
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

  public Comment toEntityObject() {
    Comment comment = new Comment();
    comment.setDescription(this.getDescription());

    return comment;
  }
}
