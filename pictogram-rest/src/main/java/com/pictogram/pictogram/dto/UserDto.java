package com.pictogram.pictogram.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * Project: pictogram
 * Date: 17-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public class UserDto {
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String email;
  private MultipartFile file;
  private String profileImage;

  public UserDto() {
  }

  public UserDto(String username, String password, String firstName,
                 String lastName, String email, MultipartFile file) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.file = file;
  }

  public UserDto(String password, String firstName,
                 String lastName, String email) {
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
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

  public MultipartFile getFile() {
    return file;
  }

  public void setFile(MultipartFile file) {
    this.file = file;
  }

  public String getProfileImage() {
    return profileImage;
  }

  public void setProfileImage(String profileImage) {
    this.profileImage = profileImage;
  }
}
