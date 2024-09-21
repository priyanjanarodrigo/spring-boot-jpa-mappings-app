# Spring JPA Mappings

## Introduction

This Spring Boot application demonstrations various JPA (Java
Persistence API) mapping techniques. It showcases the implementation and usage of key relationship
annotations such as `@OneToOne`, `@OneToMany`, and `@ManyToMany` in different scenarios and styles.

This application has 2 Spring profiles configured for JPA based table creation and liquibase based
table creation where each profile has its own configuration file and database configuration.

1. JPA based table creation profile (Default) : `local-jpa`
    - By default, the application is configured to run with JPA based table creation profile.


2. Liquibase based table creation profile : `local-liquibase`
    - Just run `mvn spring-boot:run -Dspring-boot.run.profiles=local-liquibase` to run the
      application with liquibase based table creation profile.

## References

1. All Liquibase types with related SQL & Java types: https://xenovation.com/blog/development/java/java-professional-developer/liquibase-related-sql-java-types