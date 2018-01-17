package com.pictogram.pictogram.rest.service;

import com.pictogram.pictogram.rest.model.User;
import com.pictogram.pictogram.rest.model.dto.UserDto;

/**
 * Project: pictogram
 * Date: 17-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface UserService {
  void save(UserDto userDto);

  User getCurrentUser();

}
