package com.myorg.jma.service;

import com.myorg.jma.model.dto.request.CourseRequest;
import com.myorg.jma.model.dto.response.CourseResponse;

/**
 * CourseService interface provides the contract for the required course service functionality.
 */
public interface CourseService {

  CourseResponse createCourse(CourseRequest courseRequest);
}
