package com.imperial.academia.entity.user;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Interface for a factory to create and update User entity instances.
 * This factory pattern aids in encapsulating the instantiation logic and providing flexibility
 * in creating different versions of User objects with potentially complex creation logic.
 */
public interface UpdateUserFactory {

    /**
     * Creates a new User object with the provided details.
     *
     * @param id The unique identifier for the user.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param email The email address of the user.
     * @param role The role of the user in the system (e.g., admin, user, guest).
     * @param avatarURL The URL to the user's avatar image.
     * @param registrationDate The timestamp marking the user's registration date.
     * @param lastModifiedDate The date and time when the user's information was last modified.
     * @return Returns a new User object initialized with the provided parameters.
     */
    User create(int id, String username, String password, String email, String role, String avatarURL, Timestamp registrationDate, LocalDateTime lastModifiedDate);
}
