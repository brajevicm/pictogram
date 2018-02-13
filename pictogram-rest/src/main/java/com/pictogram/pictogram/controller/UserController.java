package com.pictogram.pictogram.controller;

import com.pictogram.pictogram.dto.UserDto;
import com.pictogram.pictogram.exception.user.UserAlreadyAuthorizedException;
import com.pictogram.pictogram.exception.user.UserNotAuthorizedException;
import com.pictogram.pictogram.exception.user.UserNotFoundException;
import com.pictogram.pictogram.exception.user.UsernameAlreadyExistsException;
import com.pictogram.pictogram.jwt.JwtUser;
import com.pictogram.pictogram.jwt.TokenUtil;
import com.pictogram.pictogram.model.User;
import com.pictogram.pictogram.service.UserService;
import com.pictogram.pictogram.storage.StorageService;
import com.pictogram.pictogram.util.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@RestController
public class UserController {

  @Value("${jwt.header}")
  private String tokenHeader;

  @Autowired
  private TokenUtil tokenUtil;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private UserService userService;

  @Autowired
  StorageService storageService;

  @GetMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
  public JwtUser getAuthenticatedUser(HttpServletRequest request) {
    String authToken = request.getHeader(tokenHeader);
    final String token = authToken.substring(7);
    String username = tokenUtil.getUsernameFromToken(token);

    return (JwtUser) userDetailsService.loadUserByUsername(username);
  }

  @PostMapping(value = "${jwt.route.authentication.register}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<User> registerUser(@RequestParam String username,
                                             @RequestParam String password,
                                             @RequestParam String firstName,
                                             @RequestParam String lastName,
                                             @RequestParam String email,
                                             @RequestParam MultipartFile file) throws URISyntaxException {
    NullUtil.ifNotNullThrow(userService.getCurrentUser(), new UserAlreadyAuthorizedException());
    NullUtil.ifNotNullThrow(userService.findByUsername(username),
      new UsernameAlreadyExistsException(username));

    String profileImage = storageService.store(file);

    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    user.setProfileImage(profileImage);

    User newUser = userService.save(user);
    Long userId = newUser.getId();

    return ResponseEntity.ok(NullUtil.ifNullThrow(newUser, new UserNotFoundException(userId)));
  }

  @PostMapping(value = "${jwt.route.authentication.register}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {
    String username = userDto.getUsername();

    NullUtil.ifNotNullThrow(userService.getCurrentUser(), new UserAlreadyAuthorizedException());
    NullUtil.ifNotNullThrow(userService.findByUsername(userDto.getUsername()),
      new UsernameAlreadyExistsException(username));

    User user = new User();
    user.setUsername(username);
    user.setPassword(userDto.getPassword());
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setEmail(userDto.getEmail());
    user.setProfileImage(userDto.getProfileImage());

    User newUser = userService.save(user);
    Long userId = newUser.getId();

    return ResponseEntity.ok(NullUtil.ifNullThrow(newUser, new UserNotFoundException(userId)));
  }

  @GetMapping(value = "users/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable Long userId) {
    return ResponseEntity.ok(NullUtil.ifNullThrow(userService.findOne(userId), new UserNotFoundException(userId)));
  }

  @PutMapping(value = "users/{userId}")
  public ResponseEntity<User> editUserById(@PathVariable Long userId,
                                           @RequestBody UserDto userDto) {
    User user = NullUtil.ifNullThrow(userService.findOne(userId), new UserNotFoundException(userId));
    if (!userService.getCurrentUser().equals(user)) {
      throw new UserNotAuthorizedException();
    }

    user.setUsername(userDto.getUsername());
    user.setPassword(userDto.getPassword());
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setEmail(userDto.getEmail());
    user.setProfileImage(userDto.getProfileImage());
    userService.update(userId, user);

    return ResponseEntity.ok(NullUtil.ifNullThrow(userService.findOne(userId), new UserNotFoundException(userId)));
  }
}
