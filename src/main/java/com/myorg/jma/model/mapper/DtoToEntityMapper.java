package com.myorg.jma.model.mapper;

import static lombok.AccessLevel.PRIVATE;

import com.myorg.jma.model.dto.request.AddressRequest;
import com.myorg.jma.model.dto.request.CourseRequest;
import com.myorg.jma.model.dto.request.PersonalInterestRequest;
import com.myorg.jma.model.dto.request.StudentRequest;
import com.myorg.jma.model.entity.Address;
import com.myorg.jma.model.entity.Course;
import com.myorg.jma.model.entity.PersonalInterest;
import com.myorg.jma.model.entity.Student;
import java.util.List;
import lombok.NoArgsConstructor;

/**
 * DtoToEntityMapper class is responsible for mapping DTOs to entities.
 */
@NoArgsConstructor(access = PRIVATE)
public final class DtoToEntityMapper {

  /**
   * Maps AddressRequest to Address entity.
   *
   * @param addressRequest AddressRequest object.
   * @return Address.
   */
  public static Address mapAddressRequestToAddress(AddressRequest addressRequest) {
    return Address.builder()
        .residenceNo(addressRequest.residenceNo())
        .addressLine1(addressRequest.addressLine1())
        .addressLine2(addressRequest.addressLine2())
        .city(addressRequest.city())
        .build();
  }

  /**
   * Maps StudentRequest to Student entity.
   *
   * @param studentRequest StudentRequest object.
   * @return Student.
   */
  public static Student mapStudentRequestToStudent(StudentRequest studentRequest) {
    Student student = Student.builder()
        .name(studentRequest.name())
        .age(studentRequest.age())
        .address(mapAddressRequestToAddress(studentRequest.address()))
        .build();

    /* It is important to set the student in each personal interest object before persistence. This
     is a key point in the one-to-many relationship.*/
    List<PersonalInterest> personalInterests = mapListOfPersonalInterestRequestsToListOfPersonalInterests(
        studentRequest.personalInterests(), student);
    student.setPersonalInterests(personalInterests);

    return student;
  }

  /**
   * Maps list of PersonalInterestRequest to list of PersonalInterest entities.
   *
   * @param personalInterestRequests List of PersonalInterestRequest objects.
   * @param student                  Student entity.
   * @return List<PersonalInterest>.
   */
  public static List<PersonalInterest> mapListOfPersonalInterestRequestsToListOfPersonalInterests(
      List<PersonalInterestRequest> personalInterestRequests, Student student) {
    return personalInterestRequests.stream()
        .map(request -> mapPersonalInterestRequestToPersonalInterest(request, student)).toList();
  }

  /**
   * Maps PersonalInterestRequest to PersonalInterest entity.
   *
   * @param personalInterestRequest PersonalInterestRequest object.
   * @param student                 Student entity.
   * @return PersonalInterest.
   */
  public static PersonalInterest mapPersonalInterestRequestToPersonalInterest(
      PersonalInterestRequest personalInterestRequest, Student student) {
    return PersonalInterest
        .builder()
        .description(personalInterestRequest.description())
        .student(student)
        .build();
  }

  /**
   * Maps CourseRequest to Course entity.
   *
   * @param courseRequest CourseRequest object.
   * @return Course.
   */
  public static Course mapCourseRequestToCourse(CourseRequest courseRequest) {
    return Course.builder().name(courseRequest.name()).fee(courseRequest.fee()).build();
  }
}
