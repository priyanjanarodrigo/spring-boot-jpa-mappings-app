package com.myorg.jma.util;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

/**
 * Constants class contains common constants.
 */
@NoArgsConstructor(access = PRIVATE)
public final class Constants {

  public static final String DOT = ".";
  public static final int INT_ONE = 1;
  public static final String PATH_SEPARATOR = "/";
  public static final String POSTGRES_15_DOCKER_IMAGE = "postgres:15";
  public static final String PROFILE_LOCAL_JPA = "local-jpa";
  public static final String PROFILE_LOCAL_LIQUIBASE = "local-liquibase";
  public static final String URI_ENDPOINT_COURSES = "/api/v1/courses";
  public static final String URI_ENDPOINT_STUDENTS = "/api/v1/students";
}
