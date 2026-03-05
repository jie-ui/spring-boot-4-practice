package com.example.practice.controller;

import com.example.practice.model.User;
import com.example.practice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for User CRUD operations.
 *
 * <p>Demonstrates:
 * <ul>
 *   <li>{@code @PostMapping} / {@code @PutMapping} / {@code @DeleteMapping}</li>
 *   <li>{@code @PathVariable} — reads {id} from the URL path</li>
 *   <li>{@code @RequestBody} — deserialises JSON request body into a Java object</li>
 *   <li>{@code @Valid} — triggers Bean Validation on the request body</li>
 *   <li>{@code ResponseEntity} — gives full control over HTTP status + body</li>
 * </ul>
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /** GET /api/users — list all users */
    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    /** GET /api/users/{id} — get one user */
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.findById(id);
    }

    /** POST /api/users — create a new user */
    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        User created = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /** PUT /api/users/{id} — update an existing user */
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @Valid @RequestBody User user) {
        return userService.update(id, user);
    }

    /** DELETE /api/users/{id} — delete a user */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
