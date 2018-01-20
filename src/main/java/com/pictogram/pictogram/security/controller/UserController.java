package com.pictogram.pictogram.security.controller;

import com.pictogram.pictogram.rest.model.dto.UserDto;
import com.pictogram.pictogram.rest.service.UserService;
import com.pictogram.pictogram.security.utils.TokenUtil;
import com.pictogram.pictogram.security.model.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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

  @GetMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
  public JwtUser getAuthenticatedUser(HttpServletRequest request) {
    String authToken = request.getHeader(tokenHeader);
    final String token = authToken.substring(7);
    String username = tokenUtil.getUsernameFromToken(token);

    return (JwtUser) userDetailsService.loadUserByUsername(username);
  }

  @Autowired
  private UserService userService;

  @PostMapping(value = "${jwt.route.authentication.register}", consumes = {"multipart/form-data"})
  public ResponseEntity<?> registerUser(@RequestParam String username,
                                        @RequestParam String password,
                                        @RequestParam String firstName,
                                        @RequestParam String lastName,
                                        @RequestParam String email,
                                        @RequestParam MultipartFile file) throws IOException {

    UserDto userDto = new UserDto(username, password, firstName, lastName, email, file);

    userService.save(userDto);

    return ResponseEntity.ok("User successfully created");
  }
}
