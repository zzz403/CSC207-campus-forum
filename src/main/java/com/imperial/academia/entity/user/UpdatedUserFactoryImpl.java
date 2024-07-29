package com.imperial.academia.entity.user;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Implementation of the UpdateUserFactory interface for creating User objects.
 * This class provides the concrete implementation of the factory method to instantiate User entities.
 */
public class UpdatedUserFactoryImpl implements UpdateUserFactory {

    /**
     * Creates a new User object with specified details. This method instantiates a User with parameters provided.
     *
     * @param id The unique identifier for the user.
     * @param username The username of the user.
     * @param password The password for the user's account.
     * @param email The email address of the user.
     * @param role The role of the user in the system, such as "admin" or "user".
     * @param avatarURL URL link to the user's avatar image.
     * @param registrationDate The timestamp when the user was registered in the system.
     * @param lastModifiedDate The date and time of the user's last profile update.
     * @return A new instance of a User configured with the specified values.
     */
    @Override
    public User create(int id, String username, String password, String email, String role, String avatarURL, Timestamp registrationDate, LocalDateTime lastModifiedDate) {
        return new User(id, username, password, email, role, avatarURL, registrationDate, Timestamp.valueOf(lastModifiedDate));
    }
}
