package com.pictogram.pictogram.security.model;

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
public class Authority {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_seq")
  @SequenceGenerator(name = "authority_seq", sequenceName = "authority_seq", allocationSize = 1)
  private Long id;

  @Column(name = "name", length = 32)
  @NotNull
  @Enumerated(EnumType.STRING)
  private AuthorityName name;

  @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
  private List<User> users;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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
      "id=" + id +
      ", name=" + name +
      ", users=" + users +
      '}';
  }
}
