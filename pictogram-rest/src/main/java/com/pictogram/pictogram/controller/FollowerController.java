package com.pictogram.pictogram.controller;

import com.pictogram.pictogram.exception.follower.FollowerNotFoundException;
import com.pictogram.pictogram.exception.follower.FollowersNotFoundException;
import com.pictogram.pictogram.exception.user.UserNotFoundException;
import com.pictogram.pictogram.model.Follower;
import com.pictogram.pictogram.service.FollowerService;
import com.pictogram.pictogram.service.UserService;
import com.pictogram.pictogram.util.BooleanUtil;
import com.pictogram.pictogram.util.NullUtil;
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
  private FollowerService followerService;

  @Autowired
  private UserService userService;

  @PostMapping(value = "followers/{followId}")
  public ResponseEntity<Follower> addFollower(@PathVariable Long followId) {
    NullUtil.ifNullThrow(userService.findOne(followId), new UserNotFoundException(followId));

    return ResponseEntity.ok(NullUtil.ifNullThrow(followerService.save(followId), new FollowerNotFoundException()));
  }

  @GetMapping(value = "followers/{userId}")
  public ResponseEntity<List<Follower>> getFollowers(@PathVariable Long userId,
                                                     @RequestParam int page,
                                                     @RequestParam int size) {
    List<Follower> followers = followerService.findAllByUser(userId, page, size);
    NullUtil.ifNullThrow(userService.findOne(userId), new UserNotFoundException(userId));

    return ResponseEntity.ok(NullUtil.ifNullThrow(followers, new FollowersNotFoundException()));
  }

  @DeleteMapping(value = "followers/{followId}")
  public ResponseEntity<Boolean> deleteFollow(@PathVariable Long followId) {
    NullUtil.ifNullThrow(userService.findOne(followId), new UserNotFoundException(followId));
    Boolean isDeleted = followerService.delete(followId);

    return ResponseEntity.ok(BooleanUtil.ifFalseThrow(isDeleted, new FollowerNotFoundException()));
  }

}
