package com.pictogram.pictogram.service;


import com.pictogram.pictogram.model.User;

/**
 * Project: pictogram
 * Date: 17-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface UserService {
  void save(User user);

  void update(Long userId, User user);

  User findOne(Long userId);

  User findByUsername(String username);

  User getCurrentUser();

}
