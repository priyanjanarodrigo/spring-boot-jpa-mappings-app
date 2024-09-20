package com.myorg.jma.model.mapper;

import static lombok.AccessLevel.PRIVATE;

import com.myorg.jma.model.dto.response.AddressResponse;
import com.myorg.jma.model.dto.response.PersonalInterestResponse;
import com.myorg.jma.model.dto.response.StudentResponse;
import com.myorg.jma.model.entity.Address;
import com.myorg.jma.model.entity.PersonalInterest;
import com.myorg.jma.model.entity.Student;
import java.util.List;
import lombok.NoArgsConstructor;

/**
 * EntityToDtoMapper class is responsible for mapping entities to DTOs.
 */
@NoArgsConstructor(access = PRIVATE)
public final class EntityToDtoMapper {

  /**
   * Maps Student entity to StudentResponse.
   *
   * @param student Student entity.
   * @return StudentResponse.
   */
  public static StudentResponse mapStudentToStudentResponse(Student student) {
    return StudentResponse.builder()
        .id(student.getId())
        .name(student.getName())
        .age(student.getAge())
        .address(mapAddressToAddressResponse(student.getAddress()))
        .personalInterests(mapListOfPersonalInterestToListOfPersonalInterestResponse(
            student.getPersonalInterests()))
        .build();
  }

  /**
   * Maps Address entity to AddressResponse.
   *
   * @param address Address entity.
   * @return AddressResponse.
   */
  public static AddressResponse mapAddressToAddressResponse(Address address) {
    return AddressResponse.builder()
        .residenceNo(address.getResidenceNo())
        .addressLine1(address.getAddressLine1())
        .addressLine2(address.getAddressLine2())
        .city(address.getCity())
        .build();
  }

  /**
   * Maps PersonalInterest entity to PersonalInterestResponse.
   *
   * @param personalInterest PersonalInterest entity.
   * @return PersonalInterestResponse.
   */
  public static PersonalInterestResponse mapPersonalInterestToPersonalInterestResponse(
      PersonalInterest personalInterest) {
    return PersonalInterestResponse.builder()
        .id(personalInterest.getId())
        .description(personalInterest.getDescription())
        .build();
  }

  /**
   * Maps list of PersonalInterest entities to a list of PersonalInterestResponse.
   *
   * @param personalInterests List of PersonalInterest entities.
   * @return List<PersonalInterestResponse>.
   */
  public static List<PersonalInterestResponse> mapListOfPersonalInterestToListOfPersonalInterestResponse(
      List<PersonalInterest> personalInterests) {
    return personalInterests.stream()
        .map(EntityToDtoMapper::mapPersonalInterestToPersonalInterestResponse).toList();
  }
}
