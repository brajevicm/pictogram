package com.pictogram.pictogram.dao;

import com.pictogram.pictogram.TimeProvider;
import com.pictogram.pictogram.domain.UserDomain;
import com.pictogram.pictogram.model.User;
import com.pictogram.pictogram.repository.UserRepository;
import com.pictogram.pictogram.service.UserService;
import com.pictogram.pictogram.model.Authority;
import com.pictogram.pictogram.domain.AuthorityName;
import com.pictogram.pictogram.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * Project: pictogram
 * Date: 17-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  TimeProvider timeProvider;

  @Autowired
  StorageService storageService;

  @Override
  public void save(UserDomain userDomain) {
//    String profileImage;
//    if (userDomain.getFile() == null) {
//      profileImage = userDomain.getProfileImage();
//    } else {
//      profileImage = storageService.store(userDomain.getFile());
//    }

    User user = toEntityObject(userDomain);

    userRepository.save(user);
  }

  @Override
  public void update(Long userId, UserDomain userDomain) {
    User user = userRepository.findOne(userId);
    user.setFirstName(userDomain.getFirstName());
    user.setLastName(userDomain.getFirstName());
    user.setEmail(userDomain.getEmail());
    user.setPassword(userDomain.getPassword());
    user.setProfileImage(userDomain.getProfileImage());

    userRepository.save(user);
  }

  @Override
  public UserDomain findOne(Long userId) {
    return userRepository.findOne(userId);
  }

  @Override
  public UserDomain findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public UserDomain getCurrentUser() {
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

  public static User toEntityObject(UserDomain userDomain) {
    User user = new User();
    user.setId(userDomain.getId());
    user.setUsername(userDomain.getUsername());
    user.setPassword(hashPassword(userDomain.getPassword()));
    user.setFirstName(userDomain.getFirstName());
    user.setLastName(userDomain.getLastName());
    user.setUsername(userDomain.getUsername());
    user.setPassword(userDomain.getPassword());
    user.setEmail(userDomain.getEmail());
    user.setCreatedDate(userDomain.getCreatedDate());
    user.setLastPasswordResetDate(userDomain.getLastPasswordResetDate());
    user.setEnabled(userDomain.isEnabled());
    user.setProfileImage(userDomain.getProfileImage());
    user.setAuthorities(userDomain.getAuthorities());

    return user;
  }
}
