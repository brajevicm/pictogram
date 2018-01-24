package com.pictogram.pictogram.factory;

import com.pictogram.pictogram.domain.AuthorityDomain;
import com.pictogram.pictogram.domain.UserDomain;
import com.pictogram.pictogram.model.JwtUser;
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

  public static JwtUser create(UserDomain user) {
    return new JwtUser(
      user.getId(),
      user.getUsername(),
      user.getPassword(),
      user.getFirstName(),
      user.getLastName(),
      user.getEmail(),
      user.getProfileImage(),
      user.isEnabled(),
      user.getCreatedDate(),
      user.getLastPasswordResetDate(),
      mapToGrantedAuthorities(user.getAuthorities())
    );
  }

  private static List<GrantedAuthority> mapToGrantedAuthorities(List<AuthorityDomain> authorities) {
    return authorities.stream()
      .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
      .collect(Collectors.toList());
  }
}
