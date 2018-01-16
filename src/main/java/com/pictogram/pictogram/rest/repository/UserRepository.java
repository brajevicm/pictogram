package com.pictogram.pictogram.rest.repository;

import com.pictogram.pictogram.rest.model.User;
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
