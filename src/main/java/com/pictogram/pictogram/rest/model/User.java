package com.pictogram.pictogram.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pictogram.pictogram.commons.model.AbstractEntity;
import com.pictogram.pictogram.security.model.Authority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/*
  id?: number;//
  role_id?: number;//
  role?: string;//
  flag_id?: number;//
  flag?: string;//
  username?: string;
  password?: string;
  firstName?: string;
  lastName?: string;
  image?: string;//
  token?: string;//
  profile_image?: string; //
 */
/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

  @Column(name = "username", length = 32, unique = true, nullable = false)
  @NotNull
  @Size(min = 2, max = 32)
  private String username;

  @Column(name = "password", nullable = false)
  @NotNull
  @Size(min = 8, max = 255)
  private String password;

  @Column(name = "first_name", length = 32, nullable = false)
  @NotNull
  @Size(min = 2, max = 32)
  private String firstName;

  @Column(name = "last_name", length = 32, nullable = false)
  @NotNull
  @Size(min = 2, max = 32)
  private String lastName;

  @Column(name = "email", unique = true, nullable = false)
  @NotNull
  @Size(min = 4, max = 255)
  private String email;

  @Column(name = "profile_image", nullable = false)
  @NotNull
  @Size(min = 3, max = 255)
  private String profileImage;

  @Column(name = "enabled", nullable = false)
  @NotNull
  private boolean enabled;

  @Column(name = "created_date")
  @Temporal(TemporalType.TIMESTAMP)
  @NotNull
  private Date createdDate;

  @Column(name = "last_password_reset_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastPasswordResetDate;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
    name = "user_authorities",
    joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")}
  )
  private List<Authority> authorities;

  @OneToMany(
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    mappedBy = "user")
  private List<Post> posts = new ArrayList<>();

  @OneToMany(
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    mappedBy = "user")
  private List<Comment> comments = new ArrayList<>();

//  private List<Post> upvotedPosts = new ArrayList<>();
//
//  private List<Comment> upvotedComments = new ArrayList<>();
//
//  private List<Post> reportedPosts = new ArrayList<>();
//
//  private List<Comment> reportedComments = new ArrayList<>();

  public User() {
  }

  public User(String username, String password, String firstName, String lastName, String email,
              String profileImage, boolean enabled, Date createdDate, Date lastPasswordResetDate,
              List<Authority> authorities, List<Post> posts, List<Comment> comments) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.profileImage = profileImage;
    this.enabled = enabled;
    this.createdDate = createdDate;
    this.lastPasswordResetDate = lastPasswordResetDate;
    this.authorities = authorities;
    this.posts = posts;
    this.comments = comments;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @JsonIgnore
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getProfileImage() {
    return profileImage;
  }

  public void setProfileImage(String profileImage) {
    this.profileImage = profileImage;
  }

  @JsonIgnore
  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  @JsonIgnore
  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  @JsonIgnore
  public Date getLastPasswordResetDate() {
    return lastPasswordResetDate;
  }

  public void setLastPasswordResetDate(Date lastPasswordResetDate) {
    this.lastPasswordResetDate = lastPasswordResetDate;
  }

  public List<Authority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(List<Authority> authorities) {
    this.authorities = authorities;
  }

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public void addComment(Comment comment) {
    comments.add(comment);
    comment.setUser(this);
  }

  public void removeComment(Comment comment) {
    comments.remove(comment);
    comment.setUser(null);
  }

  public void addPost(Post post) {
    posts.add(post);
    post.setUser(this);
  }

  public void removePost(Post post) {
    posts.remove(post);
    post.setUser(null);
  }

//  public void upvotePost(Post post) {
//    upvotedPosts.add(post);
//  }
//
//  public void upvoteComment(Comment comment) {
//    upvotedComments.add(comment);
//  }
//
//  public void reportPost(Post post) {
//    reportedPosts.add(post);
//  }
//
//  public void reportComment(Comment comment) {
//    reportedComments.add(comment);
//  }

}
