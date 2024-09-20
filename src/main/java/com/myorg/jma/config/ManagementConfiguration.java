package com.myorg.jma.config;

import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ManagementConfiguration class contains management/monitoring endpoints related beans.
 */
@Configuration
public class ManagementConfiguration {

  /**
   * Bean for HttpExchangeRepository.
   *
   * @return InMemoryHttpExchangeRepository.
   */
  @Bean(name = "httpTraceRepository")
  public HttpExchangeRepository httpTraceRepository() {
    return new InMemoryHttpExchangeRepository();
  }
}
