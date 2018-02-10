package com.pictogram.pictogram.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Table(name = "comments")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Getter
  @Setter
  private Long id;

  @Column(name = "description", unique = true, nullable = false)
  @Getter
  @Setter
  private String description;

  @Column(name = "created_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @Getter
  @Setter
  private Date createdDate;

  @Column(name = "enabled", nullable = false)
  @Getter
  @Setter
  private boolean enabled;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  @Getter
  @Setter
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  @Getter
  @Setter
  private Post post;

  @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
  @Getter
  @Setter
  private List<UpvoteComment> upvoteComments;

  @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
  @Getter
  @Setter
  private List<ReportComment> reportComments;

  @Transient
  @Getter
  @Setter
  private boolean upvoted;

  @Transient
  @Getter
  @Setter
  private boolean reported;
}
