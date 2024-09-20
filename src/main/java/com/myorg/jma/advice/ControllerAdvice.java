package com.myorg.jma.advice;

import static com.myorg.jma.model.mapper.ErrorResponseMapper.mapToApiErrorResponse;
import static com.myorg.jma.util.Constants.DOT;
import static com.myorg.jma.util.Constants.INT_ONE;
import static java.util.stream.Collectors.toMap;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.myorg.jma.model.dto.response.ApiErrorResponse;
import com.myorg.jma.model.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ControllerAdvice class handles the exceptions and provides appropriate responses with the
 * configured status codes for each exception type.
 */
@RestControllerAdvice
public class ControllerAdvice {

  /**
   * Handles the MethodArgumentNotValidException and returns a map of field errors.
   *
   * @param methodArgumentNotValidException The MethodArgumentNotValidException object.
   * @return Map<String, String>.
   */
  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler({MethodArgumentNotValidException.class})
  public Map<String, String> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException methodArgumentNotValidException) {

    return methodArgumentNotValidException
        .getBindingResult()
        .getFieldErrors()
        .stream()
        .collect(toMap(
            FieldError::getField,
            DefaultMessageSourceResolvable::getDefaultMessage,
            (firstKey, secondKey) -> secondKey,
            LinkedHashMap::new)
        );
  }

  /**
   * Handles the ConstraintViolationException and returns a map of constraint violations.
   *
   * @param constraintViolationException The ConstraintViolationException object.
   * @return Map<String, String>.
   */
  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler({ConstraintViolationException.class})
  public Map<String, String> handleConstraintViolationException(
      ConstraintViolationException constraintViolationException) {

    return constraintViolationException
        .getConstraintViolations()
        .stream()
        .collect(toMap(
            constraintViolation -> {
              String propertyPath = constraintViolation.getPropertyPath().toString();
              return (propertyPath.contains(DOT))
                  ? propertyPath.substring(propertyPath.lastIndexOf(DOT) + INT_ONE)
                  : propertyPath;
            },
            ConstraintViolation::getMessage
        ));
  }

  /**
   * Handles the RuntimeException and returns an ApiErrorResponse object with the status code 500 -
   * Internal Server Error.
   *
   * @param runtimeException   The RuntimeException object.
   * @param httpServletRequest The HttpServletRequest object.
   * @return ResponseEntity<ApiErrorResponse>.
   */
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ApiErrorResponse> handleInternalServerException(
      RuntimeException runtimeException, HttpServletRequest httpServletRequest) {
    return this.createErrorResponse(runtimeException, httpServletRequest, INTERNAL_SERVER_ERROR);
  }

  /**
   * Handles the ResourceNotFoundException and returns an ApiErrorResponse object with the status
   * code 404 - Not Found.
   *
   * @param resourceNotFoundException The ResourceNotFoundException object.
   * @param httpServletRequest        The HttpServletRequest object.
   * @return ResponseEntity<ApiErrorResponse>.
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(
      ResourceNotFoundException resourceNotFoundException, HttpServletRequest httpServletRequest) {
    return this.createErrorResponse(resourceNotFoundException, httpServletRequest, NOT_FOUND);
  }

  /**
   * Creates a ResponseEntity object of type ApiErrorResponse based on the provided params and
   * returns it.
   *
   * @param runtimeException   The RuntimeException object.
   * @param httpServletRequest The HttpServletRequest object.
   * @param httpStatus         The HttpStatus object.
   * @return ResponseEntity<ApiErrorResponse>.
   */
  private ResponseEntity<ApiErrorResponse> createErrorResponse(RuntimeException runtimeException,
      HttpServletRequest httpServletRequest, HttpStatus httpStatus) {
    return new ResponseEntity<>(
        mapToApiErrorResponse(runtimeException, httpServletRequest, httpStatus), httpStatus);
  }
}
