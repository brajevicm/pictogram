package com.pictogram.pictogram.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public class JwtUser implements UserDetails {

  private static final long serialVersionUID = -4217408072080621749L;

  private final Long id;
  private final String username;
  private final String password;
  private final String firstName;
  private final String lastName;
  private final String email;
  private final String profileImage;
  private final boolean enabled;
  private final Date createdDate;
  private final Date lastPasswordResetDate;
  private final Collection<? extends GrantedAuthority> authorities;

  public JwtUser(Long id,
                 String username,
                 String password,
                 String firstName,
                 String lastName,
                 String email,
                 String profileImage,
                 boolean enabled,
                 Date createdDate,
                 Date lastPasswordResetDate,
                 Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.profileImage = profileImage;
    this.enabled = enabled;
    this.createdDate = createdDate;
    this.lastPasswordResetDate = lastPasswordResetDate;
    this.authorities = authorities;
  }

  @JsonIgnore
  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getProfileImage() {
    return profileImage;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public Date getLastPasswordResetDate() {
    return lastPasswordResetDate;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @JsonIgnore
  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
