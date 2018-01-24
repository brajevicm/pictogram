package com.pictogram.pictogram.domain;

import lombok.*;

/**
 * Project: pictogram
 * Date: 25-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FollowerDomain {
  @Getter
  @Setter
  private UserDomain user, following;
}
