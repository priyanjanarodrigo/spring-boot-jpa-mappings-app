package com.myorg.jma.controller;

import static com.myorg.jma.util.Constants.PATH_SEPARATOR;
import static com.myorg.jma.util.Constants.URI_ENDPOINT_STUDENTS;
import static java.net.URI.create;

import com.myorg.jma.model.dto.request.StudentRequest;
import com.myorg.jma.model.dto.response.StudentResponse;
import com.myorg.jma.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * StudentController class includes the REST endpoints for the student related functionality.
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(URI_ENDPOINT_STUDENTS)
public class StudentController {

  private final StudentService studentService;

  /**
   * Creates a new student and returns the ResponseEntity of type StudentResponse.
   *
   * @param studentRequest The StudentRequest object which contains the student details.
   * @return ResponseEntity<StudentResponse>.
   */
  @PostMapping
  public ResponseEntity<StudentResponse> createStudent(
      @Valid
      @RequestBody StudentRequest studentRequest) {
    log.info("createStudent endpoint invoked. Request body: {}", studentRequest);
    StudentResponse studentResponse = studentService.createStudent(studentRequest);
    return ResponseEntity.created(create(URI_ENDPOINT_STUDENTS + PATH_SEPARATOR + studentResponse.id()))
        .body(studentResponse);
  }

  /**
   * Returns the student information related to the given student id within a ResponseEntity of type
   * StudentResponse.
   *
   * @param id The Student id.
   * @return ResponseEntity<StudentResponse>.
   */
  @GetMapping("/{id}")
  public ResponseEntity<StudentResponse> getStudentById(
      @Valid
      @UUID(message = "{student.controller.pathVariable.id.isValidUUID}") @PathVariable String id) {
    return ResponseEntity.ok().body(studentService.getStudentById(id));
  }

  /**
   * Deletes the student related to the given student id.
   *
   * @param id The Student id.
   * @return ResponseEntity<Void>.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteStudentById(
      @Valid
      @UUID(message = "{student.controller.pathVariable.id.isValidUUID}") @PathVariable String id) {
    studentService.deleteStudentById(id);
    return ResponseEntity.noContent().build();
  }
}
