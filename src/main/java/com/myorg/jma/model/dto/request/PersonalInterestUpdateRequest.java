package com.myorg.jma.model.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 * PersonalInterestUpdateRequest record includes personal interest updatable information.
 *
 * @param description Description of the personal interest.
 */
public record PersonalInterestUpdateRequest(
    @NotBlank(message = "{personalInterest.request.description.notBlank}") String description
) {

}
