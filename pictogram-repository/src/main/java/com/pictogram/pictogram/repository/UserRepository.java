package com.pictogram.pictogram.repository;

import com.pictogram.pictogram.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public interface UserRepository extends CrudRepository<User, Long> {
  User findByUsername(String username);
}
