package com.myorg.jma.util;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

/**
 * Constants class contains common constants.
 */
@NoArgsConstructor(access = PRIVATE)
public final class Constants {

  public static final String STUDENTS_ENDPOINT = "/api/v1/students";
  public static final String PATH_SEPARATOR = "/";
  public static final String DOT = ".";
  public static final int INT_ONE = 1;
}
