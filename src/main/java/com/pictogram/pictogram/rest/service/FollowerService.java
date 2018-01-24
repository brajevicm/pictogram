package com.pictogram.pictogram.rest.service;

/**
 * Project: pictogram
 * Date: 23-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface FollowerService {
  void save(Long userId, Long followingId);
}
