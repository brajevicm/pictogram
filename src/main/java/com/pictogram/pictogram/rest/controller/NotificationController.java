package com.pictogram.pictogram.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project: pictogram
 * Date: 21-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@RestController
public class NotificationController {

  @GetMapping(value = "notifications")
  public ResponseEntity<?> getNotifications() {

    return ResponseEntity.ok("");
  }
}
