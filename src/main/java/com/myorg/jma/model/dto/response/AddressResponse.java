package com.myorg.jma.model.dto.response;

import lombok.Builder;

/**
 * AddressResponse record includes address information to be included within an address response.
 *
 * @param residenceNo  Residence No.
 * @param addressLine1 Address Line 1.
 * @param addressLine2 Address Line 2.
 */
@Builder
public record AddressResponse(
    String residenceNo,
    String addressLine1,
    String addressLine2
) {

}
