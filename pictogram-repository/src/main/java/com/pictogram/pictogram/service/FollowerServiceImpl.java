package com.pictogram.pictogram.service;

import com.pictogram.pictogram.model.Follower;
import com.pictogram.pictogram.model.User;
import com.pictogram.pictogram.repository.FollowerRepository;
import com.pictogram.pictogram.util.TimeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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
  UserService userService;

  @Autowired
  TimeProvider timeProvider;

  @Override
  public Follower save(Long followId) {
    User user = userService.getCurrentUser();
    User follow = userService.findOne(followId);

    Follower follower = new Follower();
    follower.setUser(user);
    follower.setFollow(follow);
    follower.setCreatedDate(timeProvider.now());

    return followerRepository.save(follower);
  }

  @Override
  public List<Follower> findAllByUser(Long userId, int page, int size) {
    User user = userService.findOne(userId);
    PageRequest pageable = new PageRequest(page, size);

    return pageToFollowerList(followerRepository.findAllByUser(user, pageable));
  }

  @Override
  public List<Follower> findAllByUser(User user) {
    return followerRepository.findAllByUser(user);
  }

  @Override
  public Boolean delete(Long followId) {
    Boolean isDeleted = Boolean.FALSE;
    User user = userService.getCurrentUser();
    User follow = userService.findOne(followId);
    Follower follower = followerRepository.findByUserAndFollow(user, follow);

    if (userService.getCurrentUser().equals(follower.getUser())) {
      followerRepository.delete(follower);
      isDeleted = Boolean.TRUE;
    }

    return isDeleted;
  }

  private List<Follower> pageToFollowerList(Page<Follower> followerPage) {
    return followerPage.getContent();
  }

}
