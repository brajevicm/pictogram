package com.pictogram.pictogram.security;

import com.pictogram.pictogram.security.model.Authority;
import com.pictogram.pictogram.security.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public final class JwtUserFactory {

  private JwtUserFactory() {
  }

  public static JwtUser create(User user) {
    return new JwtUser(
      user.getId(),
      user.getUsername(),
      user.getPassword(),
      user.getFirstName(),
      user.getLastName(),
      user.getEmail(),
      mapToGrantedAuthorities(user.getAuthorities()),
      user.isEnabled()
    );
  }

  private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
    return authorities.stream()
      .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
      .collect(Collectors.toList());
  }
}
