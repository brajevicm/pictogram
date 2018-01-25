package com.pictogram.pictogram.domain;

import lombok.*;

import java.util.Date;

/**
 * Project: pictogram
 * Date: 24-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Action {
  @Getter
  @Setter
  private Long id;

  @Getter
  @Setter
  private UserDomain user;

  @Getter
  @Setter
  private Date date;

  @Getter
  @Setter
  private boolean seen;
}
