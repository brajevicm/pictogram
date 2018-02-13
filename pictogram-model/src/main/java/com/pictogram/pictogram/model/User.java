package com.pictogram.pictogram.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Getter
  @Setter
  private Long id;

  @Column(name = "username", nullable = false)
  @Getter
  @Setter
  private String username;

  @Column(name = "password", nullable = false)
  @Getter(onMethod = @__(@JsonIgnore))
  @Setter
  private String password;

  @Column(name = "first_name", length = 32, nullable = false)
  @Getter
  @Setter
  private String firstName;

  @Column(name = "last_name", length = 32, nullable = false)
  @Getter
  @Setter
  private String lastName;

  @Column(name = "email", unique = true, nullable = false)
  @Getter
  @Setter
  private String email;

  @Column(name = "profile_image", nullable = false)
  @Getter
  @Setter
  private String profileImage;

  @Column(name = "enabled", nullable = false)
  @Getter(onMethod = @__(@JsonIgnore))
  @Setter
  private boolean enabled;

  @Column(name = "created_date")
  @Temporal(TemporalType.TIMESTAMP)
  @Getter(onMethod = @__(@JsonIgnore))
  @Setter
  private Date createdDate;

  @Column(name = "last_password_reset_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @Getter(onMethod = @__(@JsonIgnore))
  @Setter
  private Date lastPasswordResetDate;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
    name = "user_authorities",
    joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")}
  )
  @Getter(onMethod = @__(@JsonIgnore))
  @Setter
  private List<Authority> authorities;

}
