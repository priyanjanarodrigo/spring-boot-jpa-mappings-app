package com.myorg.jma.integration;


import static com.myorg.jma.util.Constants.URI_ENDPOINT_COURSES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.myorg.jma.model.dto.request.CourseRequest;
import com.myorg.jma.model.dto.response.CourseResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

/**
 * CourseIntegrationTest : Integration test class for CourseController.
 */
public class CourseIntegrationTest extends AbstractIntegrationTest {

  /**
   * Test method to verify successful course creation.
   */
  @Test
  @DisplayName("Integration test - Successful course creation")
  public void testCreateCourse_successfulCourseCreation() throws Exception {
    // Preparing the course request
    CourseRequest courseRequest = new CourseRequest("Advanced Java Programming", 599.99);

    // Perform the POST request for creating the course
    MvcResult result = mockMvc.perform(post(URI_ENDPOINT_COURSES)
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(courseRequest)))
        .andExpect(status().isCreated())
        .andExpect(header().exists(LOCATION))
        .andExpect(jsonPath("$.name").value(courseRequest.name()))
        .andExpect(jsonPath("$.fee").value(courseRequest.fee()))
        .andExpect(jsonPath("$.id").exists())
        .andReturn();

    // Retrieving the response content as string
    String responseContent = result.getResponse().getContentAsString();

    // Converting the String response in to CourseResponse object
    CourseResponse courseResponse = objectMapper.readValue(responseContent, CourseResponse.class);
    assertNotNull(courseResponse, "CourseResponse object should not be null");
    assertEquals(courseRequest.name(), courseResponse.name(),
        "CourseResponse - name is expected to be equal to the CourseRequest - name");
    assertEquals(courseRequest.fee(), courseResponse.fee(),
        "CourseResponse - fee is expected to be equal to the CourseRequest - fee");
  }
}
