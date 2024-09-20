package com.myorg.jma.service;

import com.myorg.jma.model.dto.request.StudentRequest;
import com.myorg.jma.model.dto.response.StudentResponse;

/**
 * StudentService interface provides the contract for the required student service functionality.
 */
public interface StudentService {

  StudentResponse createStudent(StudentRequest studentRequest);

  StudentResponse getStudentById(String id);

  void deleteStudentById(String id);
}
