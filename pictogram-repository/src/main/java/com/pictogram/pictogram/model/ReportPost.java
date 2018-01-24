package com.pictogram.pictogram.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pictogram.pictogram.domain.PostDomain;
import com.pictogram.pictogram.domain.ReportPostDomain;
import com.pictogram.pictogram.domain.UserDomain;
import com.pictogram.pictogram.model.Action;
import com.pictogram.pictogram.model.Post;
import com.pictogram.pictogram.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Project: pictogram
 * Date: 20-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Entity
@Table(name = "reported_posts", uniqueConstraints =
@UniqueConstraint(columnNames = {"post_id", "user_id"}))
public class ReportPost extends ReportPostDomain {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Override
  public Long getId() {
    return super.getId();
  }

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  @Override
  public UserDomain getUser() {
    return super.getUser();
  }

  @Column(name = "created_date")
  @Temporal(TemporalType.TIMESTAMP)
  @Override
  public Date getDate() {
    return super.getDate();
  }

  @Column(name = "seen")
  @Override
  public boolean isSeen() {
    return super.isSeen();
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  @Override
  public PostDomain getPost() {
    return super.getPost();
  }
}
