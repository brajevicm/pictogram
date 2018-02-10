package com.pictogram.pictogram.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Project: pictogram
 * Date: 15-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "followers")
public class Follower {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Getter
  @Setter
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  @Getter
  @Setter
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "follow_id", nullable = false)
  @Getter
  @Setter
  private User follow;

  @Column(name = "created_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @Getter
  @Setter
  private Date createdDate;

}
