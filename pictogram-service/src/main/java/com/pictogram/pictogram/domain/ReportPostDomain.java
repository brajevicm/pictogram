package com.pictogram.pictogram.domain;

import lombok.*;

/**
 * Project: pictogram
 * Date: 24-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReportPostDomain extends Action{
  @Getter
  @Setter
  private PostDomain post;
}
