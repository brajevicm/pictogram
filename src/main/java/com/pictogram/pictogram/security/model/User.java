package com.pictogram.pictogram.security.model;

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
public class User {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
  @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
  private Long id;

  @Column(name = "username", length = 32, unique = true)
  @NotNull
  @Size(min = 2, max = 32)
  private String username;

  @Column(name = "password")
  @NotNull
  @Size(min = 8, max = 255)
  private String password;

  @Column(name = "firstName", length = 32)
  @NotNull
  @Size(min = 2, max = 32)
  private String firstName;

  @Column(name = "lastName", length = 32)
  @NotNull
  @Size(min = 2, max = 32)
  private String lastName;

  @Column(name = "email")
  @NotNull
  @Size(min = 4, max = 255)
  private String email;

  @Column(name = "enabled")
  @NotNull
  private boolean enabled;

  @Column(name = "profile_image")
  @NotNull
  @Size(min = 3, max = 255)
  private String profileImage;

  @Column(name = "created_date")
  @Temporal(TemporalType.TIME)
  @NotNull
  private Date createdDate;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "user_authority",
    joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")}
  )
  private List<Authority> authorities;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

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

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public String getProfileImage() {
    return profileImage;
  }

  public void setProfileImage(String profileImage) {
    this.profileImage = profileImage;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public List<Authority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(List<Authority> authorities) {
    this.authorities = authorities;
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", username='" + username + '\'' +
      ", password='" + password + '\'' +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", email='" + email + '\'' +
      ", enabled=" + enabled +
      ", profileImage='" + profileImage + '\'' +
      ", createdDate=" + createdDate +
      ", authorities=" + authorities +
      '}';
  }
}
