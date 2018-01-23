package com.pictogram.pictogram.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pictogram.pictogram.commons.model.AbstractEntity;
import com.pictogram.pictogram.security.model.Authority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

  @Column(name = "username", length = 32, unique = true, nullable = false)
  @NotNull
  @Size(min = 2, max = 32)
  private String username;

  @Column(name = "password", nullable = false)
  @NotNull
  @Size(min = 8, max = 255)
  private String password;

  @Column(name = "first_name", length = 32, nullable = false)
  @NotNull
  @Size(min = 2, max = 32)
  private String firstName;

  @Column(name = "last_name", length = 32, nullable = false)
  @NotNull
  @Size(min = 2, max = 32)
  private String lastName;

  @Column(name = "email", unique = true, nullable = false)
  @NotNull
  @Size(min = 4, max = 255)
  private String email;

  @Column(name = "profile_image", nullable = false)
  @NotNull
  @Size(min = 3, max = 255)
  private String profileImage;

  @Column(name = "enabled", nullable = false)
  @NotNull
  private boolean enabled;

  @Column(name = "created_date")
  @Temporal(TemporalType.TIMESTAMP)
  @NotNull
  private Date createdDate;

  @Column(name = "last_password_reset_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastPasswordResetDate;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
    name = "user_authorities",
    joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")}
  )
  private List<Authority> authorities;

  public User() {
  }

  public User(String username, String password, String firstName, String lastName, String email, String profileImage,
              boolean enabled, Date createdDate, Date lastPasswordResetDate, List<Authority> authorities) {
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @JsonIgnore
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getProfileImage() {
    return profileImage;
  }

  public void setProfileImage(String profileImage) {
    this.profileImage = profileImage;
  }

  @JsonIgnore
  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  @JsonIgnore
  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  @JsonIgnore
  public Date getLastPasswordResetDate() {
    return lastPasswordResetDate;
  }

  public void setLastPasswordResetDate(Date lastPasswordResetDate) {
    this.lastPasswordResetDate = lastPasswordResetDate;
  }

  @JsonIgnore
  public List<Authority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(List<Authority> authorities) {
    this.authorities = authorities;
  }

}
