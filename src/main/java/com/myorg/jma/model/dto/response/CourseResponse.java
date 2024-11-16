package com.myorg.jma.model.dto.response;

import java.util.UUID;
import lombok.Builder;

/**
 * CourseResponse record includes course information to be included within a course response.
 *
 * @param id   Course ID.
 * @param name Course name.
 * @param fee  Fee for the course.
 */
@Builder
public record CourseResponse(
    UUID id,
    String name,
    Double fee
) {

}
