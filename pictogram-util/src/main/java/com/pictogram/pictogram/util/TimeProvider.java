package com.pictogram.pictogram.util;

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

  private static final long serialVersionUID = -2838525109491932927L;

  public Date now() {
    return new Date();
  }
}
