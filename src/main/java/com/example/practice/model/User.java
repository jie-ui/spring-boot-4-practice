package com.example.practice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * JPA entity representing a user.
 *
 * <p>Demonstrates:
 * <ul>
 *   <li>{@code @Entity} — marks the class as a JPA-managed entity</li>
 *   <li>{@code @Table} — maps to the {@code users} table (avoids reserved word "user")</li>
 *   <li>{@code @Id} + {@code @GeneratedValue} — auto-increment primary key</li>
 *   <li>{@code @Column} — column-level constraints (nullable, unique)</li>
 *   <li>Jakarta Validation annotations — enforced on incoming request bodies</li>
 * </ul>
 *
 * <p>Spring Boot 4 uses Jakarta EE 11 (javax.* → jakarta.*).
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    @Column(nullable = false, unique = true)
    private String email;

    protected User() {
        // required by JPA
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}
