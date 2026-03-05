package com.example.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Spring Boot 4 practice application.
 *
 * <p>Key Spring Boot 4 concepts demonstrated in this project:
 * <ul>
 *   <li>Auto-configuration via {@code @SpringBootApplication}</li>
 *   <li>Embedded server (Tomcat) — no external container needed</li>
 *   <li>RESTful endpoints with {@code @RestController}</li>
 *   <li>Spring Data JPA with H2 in-memory database</li>
 *   <li>Bean Validation (Jakarta EE 11 via {@code @Valid})</li>
 *   <li>Actuator health/info endpoints</li>
 * </ul>
 */
@SpringBootApplication
public class PracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class, args);
    }
}
