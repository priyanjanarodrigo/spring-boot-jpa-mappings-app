package com.myorg.jma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * The main class of the Spring Boot application.
 */
@SpringBootApplication
@EnableTransactionManagement
public class SpringBootJpaMappingsAppApplication {

  /**
   * The main method to start the Spring Boot application.
   *
   * @param args The command-line arguments.
   */
  public static void main(String[] args) {
    SpringApplication.run(SpringBootJpaMappingsAppApplication.class, args);
  }
}
