package com.myorg.jma.service;

import static com.myorg.jma.model.mapper.DtoToEntityMapper.mapStudentRequestToStudent;
import static com.myorg.jma.model.mapper.EntityToDtoMapper.mapStudentToStudentResponse;
import static java.util.UUID.fromString;

import com.myorg.jma.model.dto.request.StudentRequest;
import com.myorg.jma.model.dto.response.StudentResponse;
import com.myorg.jma.model.entity.Student;
import com.myorg.jma.model.exception.ResourceNotFoundException;
import com.myorg.jma.repository.StudentRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * StudentServiceImpl class is responsible for implementing the StudentService interface.
 */
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;

  /**
   * Creates a new student and returns the student response.
   *
   * @param studentRequest The StudentRequest object which contains the student details.
   * @return StudentResponse
   */
  @Override
  public StudentResponse createStudent(StudentRequest studentRequest) {
    return mapStudentToStudentResponse(
        studentRepository.save(mapStudentRequestToStudent(studentRequest)));
  }

  /**
   * Finds and returns the student information related to the given student id. Otherwise, a
   * ResourceNotFoundException is thrown.
   *
   * @param id The Student id.
   * @return StudentResponse
   */
  @Override
  public StudentResponse getStudentById(String id) {
    return mapStudentToStudentResponse(findStudentById(fromString(id)));
  }

  /**
   * Deletes the student related to the given student id. If the student is not found, an exception
   * is thrown.
   *
   * @param id The Student id.
   */
  @Override
  public void deleteStudentById(String id) {
    studentRepository.delete(findStudentById(fromString(id)));
  }

  /**
   * Finds and returns the Student entity related to the given student id. Otherwise, a
   * ResourceNotFoundException is thrown.
   *
   * @param id The Student id.
   * @return Student
   */
  private Student findStudentById(UUID id) {
    return studentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Student not found for id: " + id));
  }
}
