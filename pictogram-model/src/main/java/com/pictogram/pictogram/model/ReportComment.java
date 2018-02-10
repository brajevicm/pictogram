package com.pictogram.pictogram.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Project: pictogram
 * Date: 20-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reported_comments")
public class ReportComment extends Action {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "comment_id", nullable = false)
  @Getter
  @Setter
  private Comment comment;

}
