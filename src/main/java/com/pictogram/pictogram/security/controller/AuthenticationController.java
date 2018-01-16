package com.pictogram.pictogram.security.controller;

import com.pictogram.pictogram.security.model.JwtAuthenticationRequest;
import com.pictogram.pictogram.security.model.JwtAuthenticationResponse;
import com.pictogram.pictogram.security.JwtTokenUtil;
import com.pictogram.pictogram.security.model.JwtUser;
import com.pictogram.pictogram.security.model.Authority;
import com.pictogram.pictogram.security.model.AuthorityName;
import com.pictogram.pictogram.rest.model.User;
import com.pictogram.pictogram.rest.repository.UserRepository;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.Date;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@RestController
@CrossOrigin(origins = "${cors.address}", maxAge = 3600)
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
    String authToken = request.getHeader(tokenHeader);
    final String token = authToken.substring(7);
    String username = jwtTokenUtil.getUsernameFromToken(token);
    JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

    if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
      String refreshedToken = jwtTokenUtil.refreshToken(token);
      return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }

  @RequestMapping(value = "${jwt.route.authentication.register}", method = RequestMethod.POST, consumes = {"multipart/form-data"})
  public ResponseEntity<?> registerUser(@RequestParam String username,
                                        @RequestParam String password,
                                        @RequestParam String firstName,
                                        @RequestParam String lastName,
                                        @RequestParam String email,
                                        @RequestParam(required = true) MultipartFile file) throws IOException {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String hashedPassword = passwordEncoder.encode(password);
    Authority authority = new Authority();
    authority.setName(AuthorityName.ROLE_USER);
    Date createdDate = new Date();
    User newUser = new User();
    newUser.setUsername(username);
    newUser.setPassword(hashedPassword);
    newUser.setFirstName(firstName);
    newUser.setLastName(lastName);
    newUser.setEnabled(true);
    newUser.setEmail(email);
    newUser.setAuthorities(Collections.singletonList(authority));

    newUser.setCreatedDate(createdDate);
    newUser.setLastPasswordResetDate(createdDate);

    newUser.setProfileImage(file.getOriginalFilename());
    System.out.println(newUser.toString());

    userRepository.save(newUser);

      String fileName = file.getOriginalFilename();
      InputStream is = file.getInputStream();

      Files.copy(is, Paths.get("C:/" + fileName),
        StandardCopyOption.REPLACE_EXISTING);

    return ResponseEntity.ok("User successfully created");
  }
}
