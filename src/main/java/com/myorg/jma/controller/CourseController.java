package com.myorg.jma.controller;

import static com.myorg.jma.util.Constants.PATH_SEPARATOR;
import static com.myorg.jma.util.Constants.URI_ENDPOINT_COURSES;
import static java.net.URI.create;

import com.myorg.jma.model.dto.request.CourseRequest;
import com.myorg.jma.model.dto.response.CourseResponse;
import com.myorg.jma.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(URI_ENDPOINT_COURSES)
public class CourseController {

  private final CourseService courseService;

  /**
   * Creates a new course and returns the ResponseEntity of type CourseResponse.
   *
   * @param courseRequest The CourseRequest object which contains the course details.
   * @return ResponseEntity<CourseResponse>.
   */
  @PostMapping
  public ResponseEntity<CourseResponse> createCourse(
      @Valid @RequestBody CourseRequest courseRequest) {
    CourseResponse courseResponse = courseService.createCourse(courseRequest);
    return ResponseEntity
        .created(create(URI_ENDPOINT_COURSES + PATH_SEPARATOR + courseResponse.id()))
        .body(courseResponse);
  }
}
