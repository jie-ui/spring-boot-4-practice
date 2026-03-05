package com.example.practice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for {@link HelloController}.
 *
 * <p>Demonstrates:
 * <ul>
 *   <li>{@code @SpringBootTest} — loads full application context</li>
 *   <li>{@code MockMvcBuilders.webAppContextSetup} — configures MockMvc from the application context</li>
 *   <li>{@code MockMvc} — performs HTTP requests and asserts responses in-process</li>
 *   <li>JSON path assertions with {@code jsonPath}</li>
 * </ul>
 */
@SpringBootTest
class HelloControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void helloWithDefaultName() throws Exception {
        mockMvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello, World! Welcome to Spring Boot 4."));
    }

    @Test
    void helloWithCustomName() throws Exception {
        mockMvc.perform(get("/api/hello").param("name", "Spring"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello, Spring! Welcome to Spring Boot 4."));
    }
}
