package com.pictogram.pictogram.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Project: com.pictogram.pictogram.model
 * Date: 10.2.18.
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@MappedSuperclass
public class Action {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Getter
  @Setter
  private Long id;

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  @Getter
  @Setter
  private User user;

  @Column(name = "created_date")
  @Temporal(TemporalType.TIMESTAMP)
  @Getter
  @Setter
  private Date createdDate;

  @Column(name = "seen")
  @Getter
  @Setter
  private boolean seen;

}