package com.imperial.academia.entity.user;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * The CommonUserFactory class is an implementation of the UserFactory interface.
 * It provides a method to create User objects with common properties.
 */
public class CommonUserFactory implements UserFactory {
    /**
     * Creates a new User with the specified details.
     * 
     * @param username The username of the user.
     * @param password The password of the user.
     * @param email The email address of the user.
     * @param registrationDate The registration date of the user.
     * @return A new User object with the specified details.
     */
    @Override
    public User create(String username, String password, String email, LocalDateTime registrationDate) {
        return new User(0, username, password, email, "user", null, Timestamp.valueOf(registrationDate), Timestamp.valueOf(registrationDate));
    }
    @Override
    public User create(int id, String username, String password, String email,String avatarURL, Timestamp registrationDate, LocalDateTime lastModifiedDate) {
        return new User(id, username, password, email, "user", avatarURL, registrationDate, Timestamp.valueOf(lastModifiedDate));
    }
}
