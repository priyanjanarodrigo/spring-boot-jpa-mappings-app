package com.myorg.jma.model.mapper;

import static lombok.AccessLevel.PRIVATE;

import com.myorg.jma.model.dto.response.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * ErrorResponseMapper class is used to map provided params to error response as appropriate.
 */
@NoArgsConstructor(access = PRIVATE)
public final class ErrorResponseMapper {

  /**
   * Maps the provided params to an ApiErrorResponse and returns it.
   *
   * @param runtimeException   RuntimeException.
   * @param httpServletRequest HttpServletRequest.
   * @param httpStatus         HttpStatus.
   * @return ApiErrorResponse.
   */
  public static ApiErrorResponse mapToApiErrorResponse(RuntimeException runtimeException,
      HttpServletRequest httpServletRequest, HttpStatus httpStatus) {
    return ApiErrorResponse.builder()
        .statusCode(httpStatus.value())
        .path(httpServletRequest.getRequestURI())
        .message(runtimeException.getMessage())
        .timestamp(LocalDateTime.now())
        .build();
  }
}
