package com.pictogram.pictogram.utils;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Component
public class TimeProvider implements Serializable {

  private static final long serialVersionUID = -3301695478208950415L;

  public Date now() {
    return new Date();
  }
}
