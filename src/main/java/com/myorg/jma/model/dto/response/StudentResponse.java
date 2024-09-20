package com.myorg.jma.model.dto.response;

import java.util.List;
import java.util.UUID;
import lombok.Builder;

/**
 * StudentResponse record includes student information to be included within a student response.
 *
 * @param id                Unique identifier for the student.
 * @param name              Name of the student.
 * @param age               Age of the student.
 * @param address           AddressResponse object containing address information.
 * @param personalInterests List of personal interests.
 */
@Builder
public record StudentResponse(
    UUID id,
    String name,
    int age,
    AddressResponse address,
    List<PersonalInterestResponse> personalInterests
) {

}
