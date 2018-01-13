package com.pictogram.pictogram.security.controller;

import com.pictogram.pictogram.security.JwtAuthenticationRequest;
import com.pictogram.pictogram.security.JwtAuthenticationResponse;
import com.pictogram.pictogram.security.JwtTokenUtil;
import com.pictogram.pictogram.security.JwtUser;
import com.pictogram.pictogram.security.model.Authority;
import com.pictogram.pictogram.security.model.AuthorityName;
import com.pictogram.pictogram.security.model.User;
import com.pictogram.pictogram.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@RestController
public class AuthenticationController {

  @Value("${jwt.header}")
  private String tokenHeader;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  UserDetailsService userDetailsService;

  @Autowired
  UserRepository userRepository;

  @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(
    @RequestBody JwtAuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {
    final Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        authenticationRequest.getUsername(),
        authenticationRequest.getPassword()
      )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    final String token = jwtTokenUtil.generateToken(userDetails, device);

    return ResponseEntity.ok(new JwtAuthenticationResponse(token));
  }

  @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
  public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
    String token = request.getHeader(tokenHeader);
    String username = jwtTokenUtil.getUsernameFromToken(token);
    JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(username);

    if (jwtTokenUtil.canTokenBeRefreshed(token, jwtUser.getLastPasswordResetDate())) {
      String refreshedToken = jwtTokenUtil.refreshToken(token);

      return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }

  @RequestMapping(value = "${jwt.route.authentication.register}", method = RequestMethod.POST)
  public ResponseEntity<?> registerUser(@RequestBody User user) throws AuthenticationException {
    Date createdDate = new Date();
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String hashedPassword = passwordEncoder.encode(user.getPassword());

    Authority authority = new Authority();
    authority.setName(AuthorityName.ROLE_USER);
    User newUser = new User();
    newUser.setUsername(user.getUsername());
    newUser.setPassword(hashedPassword);
    newUser.setFirstName(user.getFirstName());
    newUser.setLastName(user.getLastName());
    newUser.setEnabled(true);
    newUser.setEmail(user.getEmail());
    newUser.setProfileImage(user.getProfileImage());
    newUser.setCreatedDate(createdDate);
    newUser.setAuthorities(Collections.singletonList(authority));
    newUser.setLastPasswordResetDate(createdDate);
    
    userRepository.save(newUser);

    return ResponseEntity.ok("User successfully created");
  }
}
