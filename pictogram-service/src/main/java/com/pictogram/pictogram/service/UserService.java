package com.pictogram.pictogram.service;

import com.pictogram.pictogram.rest.model.User;
import com.pictogram.pictogram.dto.UserDto;

/**
 * Project: pictogram
 * Date: 17-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface UserService {
  void save(UserDto userDto);

  void update(Long userId, UserDto userDto);

  User findOne(Long userId);

  User getCurrentUser();

}
