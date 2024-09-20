package com.myorg.jma.integration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public abstract class AbstractIntegrationTest {

  protected static final String POSTGRES_DOCKER_IMAGE = "postgres:15";

  @Container
  protected static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(
      POSTGRES_DOCKER_IMAGE)
      .withDatabaseName("spring-boot-jpa-mappings-app-local-jpa-test")
      .withUsername("appUser")
      .withPassword("@66P!3-)203@s@2$or-d#0@");

  @DynamicPropertySource
  static void registerPostgresProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
  }
}
