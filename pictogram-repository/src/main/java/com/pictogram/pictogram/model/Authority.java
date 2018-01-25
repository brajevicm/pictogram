package com.pictogram.pictogram.model;

import com.pictogram.pictogram.domain.AuthorityDomain;
import com.pictogram.pictogram.domain.AuthorityName;
import com.pictogram.pictogram.domain.UserDomain;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authorities")
public class Authority extends AuthorityDomain {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Override
  public Long getId() {
    return super.getId();
  }

  @Column(name = "name", length = 32)
  @Enumerated(EnumType.STRING)
  @Override
  public AuthorityName getName() {
    return super.getName();
  }

  @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @Override
  public List<UserDomain> getUsers() {
    return super.getUsers();
  }
}
