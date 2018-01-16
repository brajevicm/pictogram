package com.pictogram.pictogram.security.repository;

import com.pictogram.pictogram.rest.projections.UserPosts;
import com.pictogram.pictogram.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@RepositoryRestResource(excerptProjection = UserPosts.class)
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);
}
