package com.pictogram.pictogram.domain;

import lombok.*;

import java.util.Date;
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
public class UserDomain {
  @Getter
  private Long id;

  @Getter
  @Setter
  private String username, password, firstName, lastName, email, profileImage;

  @Getter
  @Setter
  private boolean enabled;

  @Getter
  @Setter
  private Date createdDate, lastPasswordResetDate;

  @Getter
  @Setter
  private List<AuthorityDomain> authorities;
}
