package com.pictogram.pictogram.controller;

import com.pictogram.pictogram.TimeProvider;
import com.pictogram.pictogram.dto.UserDto;
import com.pictogram.pictogram.exception.UserNotFoundException;
import com.pictogram.pictogram.model.JwtUser;
import com.pictogram.pictogram.model.User;
import com.pictogram.pictogram.service.UserService;
import com.pictogram.pictogram.utils.TokenUtil;
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
  TimeProvider timeProvider;

  @GetMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
  public JwtUser getAuthenticatedUser(HttpServletRequest request) {
    String authToken = request.getHeader(tokenHeader);
    final String token = authToken.substring(7);
    String username = tokenUtil.getUsernameFromToken(token);

    return (JwtUser) userDetailsService.loadUserByUsername(username);
  }

  @PostMapping(value = "${jwt.route.authentication.register}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<String> registerUser(@RequestParam String username,
                                             @RequestParam String password,
                                             @RequestParam String firstName,
                                             @RequestParam String lastName,
                                             @RequestParam String email,
                                             @RequestParam MultipartFile file) throws URISyntaxException {
    User user = new User();
    String fullImagePath = "";
    try {
      byte[] bytes = file.getBytes();
      Path path = Paths.get("" + file.getOriginalFilename());
      Files.write(path, bytes);
      fullImagePath = path.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    user.setUsername(username);
    user.setPassword(password);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    user.setProfileImage(fullImagePath);
    userService.save(user);

    return ResponseEntity.created(new URI("/users")).build();
  }

  @PostMapping(value = "${jwt.route.authentication.register}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) throws URISyntaxException {
    User user = new User();
    user.setUsername(userDto.getUsername());
    user.setPassword(userDto.getPassword());
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setEmail(userDto.getEmail());
    user.setProfileImage(userDto.getProfileImage());
    user.setEnabled(true);
    user.setCreatedDate(timeProvider.now());
    user.setLastPasswordResetDate(timeProvider.now());
    user.setAuthorities(null);

    userService.save(user);

    return ResponseEntity.created(new URI("/users")).build();
  }

  @GetMapping(value = "users/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable Long userId) {
    User user = userService.findOne(userId);

    if (user == null) {
      throw new UserNotFoundException(userId);
    }

    return ResponseEntity.ok(user);
  }

  @PutMapping(value = "users/{userId}")
  public ResponseEntity<String> editUserById(@PathVariable Long userId,
                                             @RequestBody UserDto userDto) {
    User user = new User();
    user.setUsername(userDto.getUsername());
    user.setPassword(userDto.getPassword());
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setEmail(userDto.getEmail());
    user.setProfileImage(userDto.getProfileImage());
    userService.update(userId, user);

    return ResponseEntity.ok("User was successfully edited");
  }
}
