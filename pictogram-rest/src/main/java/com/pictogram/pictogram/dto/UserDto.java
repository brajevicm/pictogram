package com.pictogram.pictogram.dto;

import com.pictogram.pictogram.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * Project: pictogram
 * Date: 17-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@NoArgsConstructor
public class UserDto {

  @Getter
  @Setter
  private String username, password, firstName, lastName, email, profileImage;

  @Getter
  @Setter
  private MultipartFile file;

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

  private User toEntityObject() {
    User user = new User();
    user.setUsername(this.getUsername());
    user.setPassword(this.getPassword());
    user.setFirstName(this.getFirstName());
    user.setLastName(this.getLastName());
    user.setEmail(this.getEmail());
    user.setProfileImage(this.getProfileImage());

    return user;
  }
}
