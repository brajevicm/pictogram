package com.pictogram.pictogram.util;

/**
 * Project: com.pictogram.pictogram.util
 * Date: 12.2.18.
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public class BooleanUtil {
  public static <T, S extends Exception> T ifFalseThrow(T toCheck, S throwException) throws S {
    if (toCheck.equals(Boolean.FALSE)) {
      throw throwException;
    }
    return toCheck;
  }
}
