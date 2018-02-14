package com.pictogram.pictogram.service;

import com.pictogram.pictogram.model.Follower;
import com.pictogram.pictogram.model.User;
import com.pictogram.pictogram.repository.FollowerRepository;
import com.pictogram.pictogram.util.TimeProviderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
  private FollowerRepository followerRepository;

  @Autowired
  private UserService userService;

  @Override
  public Follower save(User user, User follow) {
    Follower follower = new Follower();
    follower.setUser(user);
    follower.setFollow(follow);
    follower.setCreatedDate(TimeProviderUtil.now());

    return followerRepository.save(follower);
  }

  @Override
  public Follower findOne(Long followerId) {
    return followerRepository.findOne(followerId);
  }

  @Override
  public List<Follower> findAllByUser(User user, Pageable pageable) {
    return pageToFollowerList(followerRepository.findAllByUser(user, pageable));
  }

  @Override
  public List<Follower> findAllByUser(User user) {
    return followerRepository.findAllByUser(user);
  }

  @Override
  public Boolean delete(User user, User follow) {
    Boolean isDeleted = Boolean.FALSE;
    Follower follower = followerRepository.findByUserAndFollow(user, follow);

    if (user.equals(follower.getUser())) {
      followerRepository.delete(follower.getId());
      isDeleted = Boolean.TRUE;
    }

    return isDeleted;
  }

  private List<Follower> pageToFollowerList(Page<Follower> followerPage) {
    return followerPage.getContent();
  }

}
