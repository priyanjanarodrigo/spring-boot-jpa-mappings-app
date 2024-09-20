package com.myorg.jma.model.exception;

import java.io.Serial;
import java.io.Serializable;
import lombok.ToString;

/**
 * ResourceNotFoundException is a custom exception class that extends RuntimeException. It is used
 * to handle resource not found scenarios in the application.
 */
@ToString
public class ResourceNotFoundException extends RuntimeException implements Serializable {

  @Serial
  private final static long serialVersionUID = -4991894606999659095L;

  private final String message;

  public ResourceNotFoundException(String message) {
    super(message);
    this.message = message;
  }
}
