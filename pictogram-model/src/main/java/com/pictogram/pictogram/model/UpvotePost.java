package com.pictogram.pictogram.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Project: pictogram
 * Date: 14-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "upvoted_posts", uniqueConstraints =
@UniqueConstraint(columnNames = {"post_id", "user_id"}))
public class UpvotePost extends Action {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  @Getter
  @Setter
  private Post post;

}
