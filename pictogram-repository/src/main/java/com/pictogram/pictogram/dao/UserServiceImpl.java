package com.pictogram.pictogram.dao;

import com.pictogram.pictogram.TimeProvider;
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
  public void save(UserDto userDto) {
    String profileImage;

    if (userDto.getFile() == null) {
      profileImage = userDto.getProfileImage();
    } else {
      profileImage = storageService.store(userDto.getFile());
    }

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
  public void update(Long userId, UserDto userDto) {
    User user = userRepository.findOne(userId);
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getFirstName());
    user.setEmail(userDto.getEmail());
    user.setPassword(userDto.getPassword());
    user.setProfileImage(userDto.getProfileImage());

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
