package com.pictogram.pictogram.controller;

import com.pictogram.pictogram.exception.follower.FollowerNotFoundException;
import com.pictogram.pictogram.exception.follower.FollowersNotFoundException;
import com.pictogram.pictogram.exception.user.UserNotAuthorizedException;
import com.pictogram.pictogram.exception.user.UserNotFoundException;
import com.pictogram.pictogram.model.Follower;
import com.pictogram.pictogram.model.User;
import com.pictogram.pictogram.service.FollowerService;
import com.pictogram.pictogram.service.UserService;
import com.pictogram.pictogram.util.BooleanUtil;
import com.pictogram.pictogram.util.EmptyUtil;
import com.pictogram.pictogram.util.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    User follow = NullUtil.ifNullThrow(userService.findOne(followId), new UserNotFoundException(followId));
    User user = NullUtil.ifNullThrow(userService.getCurrentUser(), new UserNotAuthorizedException());

    return ResponseEntity.ok(NullUtil.ifNullThrow(followerService.save(user, follow), new FollowerNotFoundException()));
  }

  @GetMapping(value = "followers/{userId}")
  public ResponseEntity<List<Follower>> getFollowers(@PathVariable Long userId,
                                                     @PageableDefault(
                                                       value = Integer.MAX_VALUE
                                                     ) Pageable pageable) {
    User user = NullUtil.ifNullThrow(userService.findOne(userId), new UserNotFoundException(userId));

    return ResponseEntity.ok(EmptyUtil.ifEmptyThrow(followerService.findAllByUser(user, pageable), new FollowersNotFoundException()));
  }

  @DeleteMapping(value = "followers/{followId}")
  public ResponseEntity<Boolean> deleteFollow(@PathVariable Long followId) {
    Follower follower = NullUtil.ifNullThrow(followerService.findOne(followId), new FollowerNotFoundException());
    User follow = NullUtil.ifNullThrow(userService.findOne(follower.getFollow().getId()), new UserNotFoundException(followId));
    User user = NullUtil.ifNullThrow(userService.getCurrentUser(), new UserNotAuthorizedException());

    return ResponseEntity.ok(BooleanUtil.ifFalseThrow(followerService.delete(user, follow), new FollowerNotFoundException()));
  }

}
