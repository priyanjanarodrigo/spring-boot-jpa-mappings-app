package com.myorg.jma.unit.service;

import static com.myorg.jma.util.Constants.PROFILE_LOCAL_JPA;
import static com.myorg.jma.util.Constants.PROFILE_LOCAL_LIQUIBASE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.myorg.jma.model.dto.request.CourseRequest;
import com.myorg.jma.model.dto.response.CourseResponse;
import com.myorg.jma.model.entity.Course;
import com.myorg.jma.repository.CourseRepository;
import com.myorg.jma.service.CourseServiceImpl;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(value = {PROFILE_LOCAL_JPA, PROFILE_LOCAL_LIQUIBASE})
class CourseServiceTest {

  @Mock
  private CourseRepository courseRepository;

  @InjectMocks
  private CourseServiceImpl courseService;

  private CourseRequest courseRequest;

  private Course course;

  private CourseResponse expectedResponse;

  @BeforeEach
  void setUp() {
    courseRequest = CourseRequest.builder()
        .name("Java Programming")
        .fee(5000.0)
        .build();

    course = Course.builder()
        .id(UUID.randomUUID())
        .name(courseRequest.name())
        .fee(courseRequest.fee())
        .build();

    expectedResponse = CourseResponse.builder()
        .id(course.getId())
        .name(course.getName())
        .fee(course.getFee())
        .build();
  }

  @Test
  @DisplayName("Unit Test - CourseService - Successfully course creation")
  void createCourse_ShouldReturnCourseResponse() {
    when(courseRepository.save(any(Course.class))).thenReturn(course);
    CourseResponse actualResponse = courseService.createCourse(courseRequest);
    
    assertThat(actualResponse).isNotNull();
    assertThat(actualResponse.id()).isEqualTo(expectedResponse.id());
    assertThat(actualResponse.name()).isEqualTo(expectedResponse.name());
    assertThat(actualResponse.fee()).isEqualTo(expectedResponse.fee());
  }
}
