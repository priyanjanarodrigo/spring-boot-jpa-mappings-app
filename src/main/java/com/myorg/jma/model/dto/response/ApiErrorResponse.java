package com.myorg.jma.model.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;

/**
 * ApiErrorResponse record is used to return a standard consistent error response throughout the
 * application.
 *
 * @param statusCode Http status code.
 * @param path       Request path.
 * @param message    Error message.
 * @param timestamp  Timestamp of the error.
 */
@Builder
public record ApiErrorResponse(
    int statusCode,
    String path,
    String message,
    LocalDateTime timestamp
) {

}