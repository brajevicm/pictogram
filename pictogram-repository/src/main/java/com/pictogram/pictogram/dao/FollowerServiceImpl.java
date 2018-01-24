package com.pictogram.pictogram.dao;

import com.pictogram.pictogram.rest.repository.FollowerRepository;
import com.pictogram.pictogram.rest.repository.UserRepository;
import com.pictogram.pictogram.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Project: pictogram
 * Date: 23-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Service
public class FollowerServiceImpl implements FollowerService {

  @Autowired
  FollowerRepository followerRepository;

  @Autowired
  UserRepository userRepository;

  @Override
  public void save(Long userId, Long followingId) {

  }
}
