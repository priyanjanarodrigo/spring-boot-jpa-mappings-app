package com.myorg.jma.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

/**
 * CourseRequest record includes course information.
 *
 * @param name Course name.
 * @param fee  Fee for the course.
 */
@Builder
public record CourseRequest(
    @NotBlank(message = "{course.request.name.notBlank}")
    String name,
    @NotNull(message = "{course.request.fee.notNull}")
    @Min(value = 0, message = "{course.request.fee.min}")
    Double fee
) {

}
