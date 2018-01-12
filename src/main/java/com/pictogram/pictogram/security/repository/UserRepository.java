package com.pictogram.pictogram.security.repository;

import com.pictogram.pictogram.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);
}
