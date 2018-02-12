package com.pictogram.pictogram.service;

import com.pictogram.pictogram.util.TimeProvider;
import com.pictogram.pictogram.model.Authority;
import com.pictogram.pictogram.model.AuthorityName;
import com.pictogram.pictogram.model.User;
import com.pictogram.pictogram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Project: pictogram
 * Date: 17-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  TimeProvider timeProvider;

  @Override
  public void save(User user) {

    userRepository.save(user);
  }

  @Override
  public void update(Long userId, User user) {
    User editedUser = userRepository.findOne(userId);
    editedUser.setFirstName(user.getFirstName());
    editedUser.setLastName(user.getLastName());
    editedUser.setEmail(user.getEmail());
    editedUser.setProfileImage(user.getProfileImage());
    editedUser.setPassword(hashPassword(user.getPassword()));

    userRepository.save(editedUser);
  }

  @Override
  public User findOne(Long userId) {
    return userRepository.findOne(userId);
  }

  @Override
  public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public User getCurrentUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();

    return userRepository.findByUsername(username);
  }

  private static String hashPassword(String password) {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    return passwordEncoder.encode(password);
  }

  private static List<Authority> getNewlyCreatedUserAuthorities() {
    Authority authority = new Authority();
    authority.setName(AuthorityName.ROLE_USER);

    return Collections.singletonList(authority);
  }
}
