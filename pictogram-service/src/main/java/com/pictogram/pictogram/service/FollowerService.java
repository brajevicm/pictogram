package com.pictogram.pictogram.service;

import com.pictogram.pictogram.model.Follower;
import com.pictogram.pictogram.model.User;

import java.util.List;

/**
 * Project: pictogram
 * Date: 23-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface FollowerService {
  void save(Long followId);

  List<Follower> findAllByUser(Long userId, int page, int size);

  List<Follower> findAllByUser(User user);

  void delete(Long followId);
}
