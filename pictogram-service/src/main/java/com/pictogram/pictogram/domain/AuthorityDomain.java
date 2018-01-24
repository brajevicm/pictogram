package com.pictogram.pictogram.domain;

import lombok.*;

import java.util.List;

/**
 * Project: pictogram
 * Date: 24-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthorityDomain {
  @Getter
  @Setter
  private AuthorityName name;

  @Getter
  @Setter
  private List<UserDomain> users;
}
