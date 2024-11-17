package com.myorg.jma.integration;

import static com.myorg.jma.util.Constants.POSTGRES_15_DOCKER_IMAGE;
import static com.myorg.jma.util.Constants.PROFILE_LOCAL_JPA;
import static com.myorg.jma.util.Constants.PROFILE_LOCAL_LIQUIBASE;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * AbstractIntegrationTest class : Abstract base class for integration tests.
 */
@Testcontainers
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    // Improves test startup time by lazy loading beans
    properties = "spring.main.lazy-initialization=true"
)
// addFilters = false to @AutoConfigureMockMvc to skip security filters during testing if they're not needed
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles(value = {PROFILE_LOCAL_JPA, PROFILE_LOCAL_LIQUIBASE})
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public abstract class AbstractIntegrationTest {

  protected final ObjectMapper objectMapper = new ObjectMapper();

  @Autowired
  protected MockMvc mockMvc;

  @Container
  protected static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(
      POSTGRES_15_DOCKER_IMAGE)
      .withDatabaseName("spring-boot-jpa-mappings-app-integration-test-db")
      .withUsername("appUser")
      .withPassword(randomUUID().toString());

  /**
   * Registers Postgres properties for integration tests.
   *
   * @param registry DynamicPropertyRegistry instance.
   */
  @DynamicPropertySource
  protected static void registerPostgresProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
  }

  /**
   * Verifies if the Postgres container is stable.
   */
  @BeforeAll
  protected static void testIsStable() {
    assertNotNull(postgreSQLContainer, "Postgres container should not be null");
    assertTrue(postgreSQLContainer.isCreated(), "Postgres container should have been created");
    assertTrue(postgreSQLContainer.isRunning(), "Postgres container should be running");
  }
}
