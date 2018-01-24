package com.pictogram.pictogram.model;

import com.pictogram.pictogram.domain.AuthorityDomain;
import com.pictogram.pictogram.domain.UserDomain;

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
@Table(name = "users")
public class User extends UserDomain {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Override
  public Long getId() {
    return super.getId();
  }

  @Column(name = "username", nullable = false)
  @Override
  public String getUsername() {
    return super.getUsername();
  }

  @Column(name = "password", nullable = false)
  @Override
  public String getPassword() {
    return super.getPassword();
  }

  @Column(name = "first_name", length = 32, nullable = false)
  @Override
  public String getFirstName() {
    return super.getFirstName();
  }

  @Column(name = "last_name", length = 32, nullable = false)
  @Override
  public String getLastName() {
    return super.getLastName();
  }

  @Column(name = "email", unique = true, nullable = false)
  @Override
  public String getEmail() {
    return super.getEmail();
  }

  @Column(name = "profile_image", nullable = false)
  @Override
  public String getProfileImage() {
    return super.getProfileImage();
  }

  @Column(name = "enabled", nullable = false)
  @Override
  public boolean isEnabled() {
    return super.isEnabled();
  }

  @Column(name = "created_date")
  @Temporal(TemporalType.TIMESTAMP)
  @Override
  public Date getCreatedDate() {
    return super.getCreatedDate();
  }

  @Column(name = "last_password_reset_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @Override
  public Date getLastPasswordResetDate() {
    return super.getLastPasswordResetDate();
  }

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
    name = "user_authorities",
    joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")}
  )
  @Override
  public List<AuthorityDomain> getAuthorities() {
    return super.getAuthorities();
  }
}
