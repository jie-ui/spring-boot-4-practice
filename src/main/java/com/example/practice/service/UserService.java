package com.example.practice.service;

import com.example.practice.model.User;
import com.example.practice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Service layer for {@link User} business logic.
 *
 * <p>Demonstrates:
 * <ul>
 *   <li>{@code @Service} — stereotype annotation that marks this as a service bean</li>
 *   <li>{@code @Transactional} — wraps methods in a database transaction</li>
 *   <li>Separation of concerns: controllers delegate to services,
 *       services delegate to repositories</li>
 * </ul>
 */
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /** Returns all users. */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /** Returns a user by ID, or throws {@link NoSuchElementException} if not found. */
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    /** Creates a new user. Throws {@link IllegalArgumentException} if email already exists. */
    @Transactional
    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException(
                    "A user with email '" + user.getEmail() + "' already exists");
        }
        return userRepository.save(user);
    }

    /** Updates name and email of an existing user. */
    @Transactional
    public User update(Long id, User updated) {
        User existing = findById(id);
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        return userRepository.save(existing);
    }

    /** Deletes a user by ID. */
    @Transactional
    public void delete(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }
}
