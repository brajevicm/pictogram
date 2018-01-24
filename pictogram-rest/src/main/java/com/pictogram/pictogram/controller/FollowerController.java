package com.pictogram.pictogram.controller;

import com.pictogram.pictogram.rest.model.Follower;
import com.pictogram.pictogram.rest.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Project: pictogram
 * Date: 21-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@RestController
public class FollowerController {

  @Autowired
  FollowerService followerService;

  //    followers/posts
  @PostMapping(value = "followers/{followerId}")
  public ResponseEntity<List<Follower>> getFollowers(@PathVariable Long followerId) {


    return ResponseEntity.ok(null);
  }
}
