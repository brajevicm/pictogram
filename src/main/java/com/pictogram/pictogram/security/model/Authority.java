package com.pictogram.pictogram.security.model;

import com.pictogram.pictogram.commons.model.AbstractEntity;
import com.pictogram.pictogram.rest.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Entity
@Table(name = "authorities")
public class Authority extends AbstractEntity {

  @Column(name = "name", length = 32)
  @NotNull
  @Enumerated(EnumType.STRING)
  private AuthorityName name;

  @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<User> users;

  public AuthorityName getName() {
    return name;
  }

  public void setName(AuthorityName name) {
    this.name = name;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  @Override
  public String toString() {
    return "Authority{" +
      ", name=" + name +
      ", users=" + users +
      '}';
  }
}
