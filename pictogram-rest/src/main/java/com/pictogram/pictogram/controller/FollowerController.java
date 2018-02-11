package com.pictogram.pictogram.controller;

import com.pictogram.pictogram.model.Follower;
import com.pictogram.pictogram.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
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

  @PostMapping(value = "followers/{followId}")
  public ResponseEntity<String> addFollower(@PathVariable Long followId) {
    followerService.save(followId);

    return ResponseEntity.ok(null);
  }

  @GetMapping(value = "followers/{userId}")
  public ResponseEntity<List<Follower>> getFollowers(@PathVariable Long userId,
                                                     @RequestParam int page,
                                                     @RequestParam int size) {
    List<Follower> followers = followerService.findAllByUser(userId, page, size);

    return ResponseEntity.ok(followers);
  }

  @DeleteMapping(value = "followers/{followId}")
  public ResponseEntity<String> deleteFollow(@PathVariable Long followId) {
    followerService.delete(followId);

    return ResponseEntity.ok("Deleted.");
  }

}
