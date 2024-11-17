package com.myorg.jma.unit.controller;

import static com.myorg.jma.util.Constants.PATH_SEPARATOR;
import static com.myorg.jma.util.Constants.PROFILE_LOCAL_JPA;
import static com.myorg.jma.util.Constants.PROFILE_LOCAL_LIQUIBASE;
import static com.myorg.jma.util.Constants.URI_ENDPOINT_COURSES;
import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myorg.jma.controller.CourseController;
import com.myorg.jma.model.dto.request.CourseRequest;
import com.myorg.jma.model.dto.response.CourseResponse;
import com.myorg.jma.service.CourseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

/**
 * CourseControllerTest : Unit test class for CourseController.
 */
@WebMvcTest(CourseController.class)
@ActiveProfiles(value = {PROFILE_LOCAL_JPA, PROFILE_LOCAL_LIQUIBASE})
class CourseControllerTest {

  /**
   * MockMvc instance for performing HTTP requests and assertions.
   */
  @Autowired
  private MockMvc mockMvc;

  /**
   * ObjectMapper instance for serializing and deserializing JSON.
   */
  @Autowired
  private ObjectMapper objectMapper;

  /**
   * Mocked CourseService instance.
   */
  @MockBean
  private CourseService courseService;

  /**
   * Generates a CourseRequest object and returns it.
   *
   * @return CourseRequest.
   */
  private CourseRequest getCourseRequest() {
    return CourseRequest.builder().name("Python 3 Core Concepts").fee(55_000.00).build();
  }

  /**
   * Generates a CourseResponse based on the given CourseRequest. Course id is set internally.
   *
   * @param courseRequest CourseRequest object.
   * @return CourseResponse.
   */
  private CourseResponse getCourseResponse(CourseRequest courseRequest) {
    return CourseResponse.builder().id(randomUUID()).name(courseRequest.name())
        .fee(courseRequest.fee()).build();
  }

  /**
   * Unit test for creating a new course with a valid request body.
   */
  @Test
  @DisplayName("Unit Test - CourseController - Creating a new course with a valid request body")
  void testCreateCourse_withValidRequest() throws Exception {
    CourseRequest courseRequest = getCourseRequest();
    CourseResponse courseResponse = getCourseResponse(courseRequest);

    when(courseService.createCourse(courseRequest)).thenReturn(courseResponse);

    mockMvc.perform(post(URI_ENDPOINT_COURSES).contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(courseRequest))).andExpect(status().isCreated())
        .andExpect(
            header().string(LOCATION, URI_ENDPOINT_COURSES + PATH_SEPARATOR + courseResponse.id()))
        .andExpect(jsonPath("$.id").value(courseResponse.id().toString()))
        .andExpect(jsonPath("$.name").value(courseResponse.name()))
        .andExpect(jsonPath("$.fee").value(courseResponse.fee()));
  }

  /**
   * Unit test for creating a new course with an invalid request body.
   */
  @Test
  @DisplayName("Unit Test - CourseController - Creating a new course with an invalid request body")
  void testCreateCourse_withInvalidRequest() throws Exception {
    CourseRequest courseRequest = CourseRequest.builder().name(null).fee(null).build();

    mockMvc.perform(post(URI_ENDPOINT_COURSES).contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(courseRequest)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.name")
            .value("name is a required property and cannot be null or empty"))
        .andExpect(jsonPath("$.fee")
            .value("fee is a required property and cannot be null"));
  }
}
