package com.example.practice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

/**
 * Global exception handler that converts common exceptions to structured HTTP responses.
 *
 * <p>Demonstrates:
 * <ul>
 *   <li>{@code @RestControllerAdvice} — intercepts exceptions from all {@code @RestController}s</li>
 *   <li>{@code @ExceptionHandler} — maps a specific exception type to an HTTP response</li>
 *   <li>{@code ProblemDetail} — RFC 9457 problem detail format (standard in Spring 6+)</li>
 * </ul>
 *
 * <p>Without this class, unhandled exceptions result in a 500 Internal Server Error.
 * With it, callers receive meaningful 4xx status codes.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maps {@link NoSuchElementException} → 404 Not Found.
     * Thrown by {@link UserService#findById(Long)} when the user does not exist.
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ProblemDetail handleNotFound(NoSuchElementException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setDetail(ex.getMessage());
        return problem;
    }

    /**
     * Maps {@link IllegalArgumentException} → 409 Conflict.
     * Thrown by {@link UserService#create(com.example.practice.model.User)} on duplicate email.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleConflict(IllegalArgumentException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problem.setDetail(ex.getMessage());
        return problem;
    }
}
