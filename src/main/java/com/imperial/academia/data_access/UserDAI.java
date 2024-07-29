package com.imperial.academia.data_access;

import com.imperial.academia.entity.user.User;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Interface for Data Access Object for User entities.
 */
public interface UserDAI {
    /**
     * Inserts a new user into the database.
     *
     * @param user the user to insert
     * @throws SQLException if a database access error occurs
     */
    void insert(User user) throws SQLException;

    /**
     * Checks if a user with the specified username exists.
     *
     * @param username the username to check
     * @return true if a user with the specified username exists, false otherwise
     * @throws SQLException if a database access error occurs
     */
    boolean existsByUsername(String username) throws SQLException;

    /**
     * Checks if a user with the specified email exists.
     *
     * @param email the email to check
     * @return true if a user with the specified email exists, false otherwise
     * @throws SQLException if a database access error occurs
     */
    boolean existsByEmail(String email) throws SQLException;

    /**
     * Retrieves a user by their username.
     *
     * @param username the username of the user to retrieve
     * @return the user with the specified username, or null if not found
     * @throws SQLException if a database access error occurs
     */
    User getByUsername(String username) throws SQLException;
    User getByEmail(String email) throws SQLException;

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user with the specified ID, or null if not found
     * @throws SQLException if a database access error occurs
     */
    User get(int id) throws SQLException;

    /**
     * Retrieves all users from the database.
     *
     * @return a list of all users
     * @throws SQLException if a database access error occurs
     */
    List<User> getAll() throws SQLException;

    /**
     * Retrieves all users that have been modified since a given timestamp.
     *
     * @param timestamp the timestamp to compare against
     * @return a list of users modified since the specified timestamp
     * @throws SQLException if a database access error occurs
     */
    List<User> getAllSince(Timestamp timestamp) throws SQLException;

    /**
     * Updates an existing user in the database.
     *
     * @param user the user to update
     * @throws SQLException if a database access error occurs
     */
    void update(User user) throws SQLException;

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     * @throws SQLException if a database access error occurs
     */
    void delete(int id) throws SQLException;
}
