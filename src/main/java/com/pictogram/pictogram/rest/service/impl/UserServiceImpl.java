package com.pictogram.pictogram.rest.service.impl;

import com.pictogram.pictogram.commons.storage.StorageService;
import com.pictogram.pictogram.commons.utils.TimeProvider;
import com.pictogram.pictogram.rest.model.User;
import com.pictogram.pictogram.rest.model.dto.UserDto;
import com.pictogram.pictogram.rest.repository.UserRepository;
import com.pictogram.pictogram.rest.service.UserService;
import com.pictogram.pictogram.security.model.Authority;
import com.pictogram.pictogram.security.model.AuthorityName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
  public void save(UserDto userDto) {
    String profileImage = storageService.store(userDto.getFile());

    User user = new User(
      userDto.getUsername(),
      hashPassword(userDto.getPassword()),
      userDto.getFirstName(),
      userDto.getLastName(),
      userDto.getEmail(),
      profileImage,
      true,
      timeProvider.now(),
      timeProvider.now(),
      getNewlyCreatedUserAuthorities()

    );

    userRepository.save(user);
  }

  @Override
  public User findOne(Long userId) {
    return userRepository.findOne(userId);
  }

  @Override
  public User getCurrentUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();

    return userRepository.findByUsername(username);
  }

  private String hashPassword(String password) {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    return passwordEncoder.encode(password);
  }

  private List<Authority> getNewlyCreatedUserAuthorities() {
    Authority authority = new Authority();
    authority.setName(AuthorityName.ROLE_USER);

    return Collections.singletonList(authority);
  }
}
