package com.pictogram.pictogram.service;

import com.pictogram.pictogram.model.Follower;
import com.pictogram.pictogram.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Project: pictogram
 * Date: 23-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface FollowerService {
  Follower save(User user, User follow);

  Follower findOne(Long followerId);

  List<Follower> findAllByUser(User user, Pageable pageable);

  List<Follower> findAllByUser(User user);

  Boolean delete(User user, User follow);
}
