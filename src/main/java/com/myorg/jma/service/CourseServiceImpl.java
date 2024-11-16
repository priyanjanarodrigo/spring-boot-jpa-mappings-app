package com.myorg.jma.service;

import static com.myorg.jma.model.mapper.DtoToEntityMapper.mapCourseRequestToCourse;
import static com.myorg.jma.model.mapper.EntityToDtoMapper.mapCourseToCourseResponse;

import com.myorg.jma.model.dto.request.CourseRequest;
import com.myorg.jma.model.dto.response.CourseResponse;
import com.myorg.jma.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * CourseServiceImpl class is responsible for implementing the CourseService interface.
 */
@Service("courseService")
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

  private final CourseRepository courseRepository;

  /**
   * Creates a new course and returns the course response.
   *
   * @param courseRequest The CourseRequest object which contains the course details.
   * @return CourseResponse.
   */
  @Override
  public CourseResponse createCourse(CourseRequest courseRequest) {
    return mapCourseToCourseResponse(
        courseRepository.save(mapCourseRequestToCourse(courseRequest)));
  }
}
