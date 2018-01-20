package com.pictogram.pictogram.security.controller;

import com.pictogram.pictogram.security.model.jwt.AuthenticationRequest;
import com.pictogram.pictogram.security.model.jwt.AuthenticationResponse;
import com.pictogram.pictogram.security.utils.TokenUtil;
import com.pictogram.pictogram.security.model.JwtUser;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
  private TokenUtil tokenUtil;

  @Autowired
  UserDetailsService userDetailsService;

  @PostMapping(value = "${jwt.route.authentication.path}")
  public ResponseEntity<?> createAuthenticationToken(
    @RequestBody AuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {
    final Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        authenticationRequest.getUsername(),
        authenticationRequest.getPassword()
      )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    final String token = tokenUtil.generateToken(userDetails, device);

    return ResponseEntity.ok(new AuthenticationResponse(token));
  }

  @GetMapping(value = "${jwt.route.authentication.refresh}")
  public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
    String authToken = request.getHeader(tokenHeader);
    final String token = authToken.substring(7);
    String username = tokenUtil.getUsernameFromToken(token);
    JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

    if (tokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
      String refreshedToken = tokenUtil.refreshToken(token);
      return ResponseEntity.ok(new AuthenticationResponse(refreshedToken));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }
}
