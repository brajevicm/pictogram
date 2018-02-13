package com.pictogram.pictogram.util;

/**
 * Project: com.pictogram.pictogram.util
 * Date: 12.2.18.
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public class NullUtil {
  private NullUtil() {
  }

  public static <T, S extends Exception> T ifNotNullThrow(T toCheck, S throwException) throws S {
    if (toCheck != null) {
      throw throwException;
    }
    return null;
  }

  public static <T, S extends Exception> T ifNullThrow(T toCheck, S throwException) throws S {
    if (toCheck == null) {
      throw throwException;
    }
    return toCheck;
  }
}
