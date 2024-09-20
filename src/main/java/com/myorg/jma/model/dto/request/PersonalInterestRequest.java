package com.myorg.jma.model.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 * PersonalInterestRequest record includes personal interest information.
 *
 * @param description Description of the personal interest.
 */
public record PersonalInterestRequest(
    @NotBlank(message = "{personalInterest.request.description.notBlank}") String description
) {

}
