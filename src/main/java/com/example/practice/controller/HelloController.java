package com.example.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * A simple "Hello World" REST controller demonstrating:
 * <ul>
 *   <li>{@code @RestController} — combines {@code @Controller} + {@code @ResponseBody}</li>
 *   <li>{@code @RequestMapping} — base path prefix for all methods</li>
 *   <li>{@code @GetMapping} — maps HTTP GET requests</li>
 *   <li>{@code @RequestParam} — reads query string parameters</li>
 *   <li>Returning a {@code Map} auto-serialised to JSON</li>
 * </ul>
 */
@RestController
@RequestMapping("/api/hello")
public class HelloController {

    /**
     * GET /api/hello
     * Returns a greeting with an optional name parameter.
     *
     * @param name the name to greet (defaults to "World")
     * @return JSON object with a {@code message} field
     */
    @GetMapping
    public Map<String, String> hello(
            @RequestParam(defaultValue = "World") String name) {
        return Map.of("message", "Hello, " + name + "! Welcome to Spring Boot 4.");
    }
}
