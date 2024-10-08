package com.myorg.jma.model.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 * AddressRequest record includes address information.
 *
 * @param residenceNo  Residence No.
 * @param addressLine1 Address Line 1.
 * @param addressLine2 Address Line 2.
 * @param city         City.
 */
public record AddressRequest(
    @NotBlank(message = "{address.request.residenceNo.notBlank}")
    String residenceNo,
    @NotBlank(message = "{address.request.addressLine1.notBlank}")
    String addressLine1,
    @NotBlank(message = "{address.request.addressLine2.notBlank}")
    String addressLine2,
    @NotBlank(message = "{address.request.city.notBlank}") String city) {

}
