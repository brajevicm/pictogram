package com.pictogram.pictogram.util;

import java.util.List;

/**
 * Project: com.pictogram.pictogram.util
 * Date: 13.2.18.
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public class EmptyUtil  {
  private EmptyUtil() {
  }

  public static <T, S extends Exception> List<T> ifEmptyThrow(List<T> toCheck, S throwException) throws S {
    if (toCheck.isEmpty()) {
      throw throwException;
    }
    return toCheck;
  }
}
