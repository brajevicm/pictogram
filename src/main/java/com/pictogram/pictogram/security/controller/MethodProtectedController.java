package com.pictogram.pictogram.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@RestController
public class MethodProtectedController {

  @RequestMapping(value = "protected", method = RequestMethod.GET)
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> getProtectedGreeting() {
    return ResponseEntity.ok("protected");
  }
}
