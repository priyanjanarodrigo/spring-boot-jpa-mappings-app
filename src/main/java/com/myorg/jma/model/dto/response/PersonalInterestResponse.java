package com.myorg.jma.model.dto.response;

import java.util.UUID;
import lombok.Builder;

/**
 * PersonalInterestResponse record includes personal interest response information.
 *
 * @param id          Unique identifier for the personal interest.
 * @param description Description about the personal interest.
 */
@Builder
public record PersonalInterestResponse(
    UUID id,
    String description
) {

}
