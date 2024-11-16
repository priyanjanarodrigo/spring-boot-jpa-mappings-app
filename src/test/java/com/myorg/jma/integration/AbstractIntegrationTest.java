package com.myorg.jma.integration;

import static com.myorg.jma.util.Constants.POSTGRES_15_DOCKER_IMAGE;
import static com.myorg.jma.util.Constants.PROFILE_LOCAL_JPA;
import static com.myorg.jma.util.Constants.PROFILE_LOCAL_LIQUIBASE;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@ActiveProfiles(value = {PROFILE_LOCAL_JPA, PROFILE_LOCAL_LIQUIBASE})
public abstract class AbstractIntegrationTest {

  @Container
  protected static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(
      POSTGRES_15_DOCKER_IMAGE)
      .withDatabaseName("spring-boot-jpa-mappings-app-integration-test-db")
      .withUsername("appUser")
      .withPassword(randomUUID().toString());

  @DynamicPropertySource
  protected static void registerPostgresProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
  }

  @BeforeAll
  protected static void testIsStable() {
    assertNotNull(postgreSQLContainer, "Postgres container should not be null");
    assertTrue(postgreSQLContainer.isCreated(), "Postgres container should have been created");
    assertTrue(postgreSQLContainer.isRunning(), "Postgres container should be running");
  }
}
