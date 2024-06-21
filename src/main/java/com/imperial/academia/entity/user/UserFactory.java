package com.imperial.academia.entity.user;

import java.time.LocalDateTime;

/**
 * The UserFactory interface defines a method for creating User objects.
 */
public interface UserFactory {
    /**
     * Creates a new User with the specified details.
     * 
     * @param username The username of the user.
     * @param password The password of the user.
     * @param email The email address of the user.
     * @param registrationDate The registration date of the user.
     * @return A new User object with the specified details.
     */
    User create(String username, String password, String email, LocalDateTime registrationDate);
}
