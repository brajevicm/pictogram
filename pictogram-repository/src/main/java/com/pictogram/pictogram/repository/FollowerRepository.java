package com.pictogram.pictogram.repository;

import com.pictogram.pictogram.model.Follower;
import com.pictogram.pictogram.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Project: pictogram
 * Date: 23-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface FollowerRepository extends PagingAndSortingRepository<Follower, Long> {
  Page<Follower> findAllByUser(User user, Pageable pageable);

  Follower findByUserAndFollow(User user, User follow);
}
