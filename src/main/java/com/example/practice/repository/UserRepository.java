package com.example.practice.repository;

import com.example.practice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for {@link User}.
 *
 * <p>Demonstrates:
 * <ul>
 *   <li>Extending {@code JpaRepository} gives CRUD methods for free:
 *       {@code save}, {@code findById}, {@code findAll}, {@code deleteById}, etc.</li>
 *   <li>Derived query methods — Spring generates the SQL from the method name.</li>
 *   <li>No implementation class needed — Spring generates a proxy at runtime.</li>
 * </ul>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Derived query: SELECT * FROM users WHERE email = ?
     */
    Optional<User> findByEmail(String email);

    /**
     * Derived query: SELECT COUNT(*) > 0 FROM users WHERE email = ?
     */
    boolean existsByEmail(String email);
}
