package com.myorg.jma.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * StudentRequest record includes student information.
 *
 * @param name              Student name.
 * @param age               Student age.
 * @param address           Address request information.
 * @param personalInterests List of personal interests.
 */
public record StudentRequest(
    @NotBlank(message = "{student.request.name.notBlank}") String name,
    @NotNull(message = "{student.request.age.notNull}")
    @Min(value = 1, message = "{student.request.age.min}") Integer age,
    @Valid
    @NotNull(message = "{student.request.address.notNull}") AddressRequest address,
    @Valid
    @NotEmpty(message = "{student.request.personalInterests.notEmpty}")
    List<PersonalInterestRequest> personalInterests) {

}
