package com.pictogram.pictogram.service;

import com.pictogram.pictogram.domain.UserDomain;

/**
 * Project: pictogram
 * Date: 17-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface UserService {
  void save(UserDomain user);

  void update(Long userId, UserDomain user);

  UserDomain findOne(Long userId);

  UserDomain getCurrentUser();

}
