package com.pictogram.pictogram.service;

import com.pictogram.pictogram.model.Authority;
import com.pictogram.pictogram.model.AuthorityName;
import com.pictogram.pictogram.model.User;
import com.pictogram.pictogram.repository.UserRepository;
import com.pictogram.pictogram.util.TimeProviderUtil;
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

  @Override
  public Boolean delete(Long userId) {
    Boolean isDeleted = Boolean.FALSE;
    Boolean isAdmin = this.getCurrentUser().getAuthorities()
      .stream()
      .anyMatch(authority -> authority.getName().equals(AuthorityName.ROLE_ADMIN));

    if (isAdmin) {
      userRepository.delete(userId);
      isDeleted = Boolean.TRUE;
    }

    return isDeleted;
  }

  @Override
  public User save(User user) {
    user.setEnabled(true);
    user.setCreatedDate(TimeProviderUtil.now());
    user.setLastPasswordResetDate(TimeProviderUtil.now());
    user.setAuthorities(getNewlyCreatedUserAuthorities());
    user.setPassword(hashPassword(user.getPassword()));

    return userRepository.save(user);
  }

  @Override
  public User update(Long userId, User user) {
    user.setPassword(hashPassword(user.getPassword()));

    return userRepository.save(user);
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
