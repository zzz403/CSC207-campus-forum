package com.imperial.academia.entity.user;

import java.sql.Timestamp;

/**
 * The User class represents a user entity with relevant details.
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;
    private String avatarUrl;
    private Timestamp registrationDate;
    private Timestamp lastModified;

    /**
     * Constructs a new User with the specified details.
     * 
     * @param id The unique identifier of the user.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param email The email address of the user.
     * @param role The role of the user (e.g., 'user', 'admin').
     * @param avatarUrl The URL of the user's avatar.
     * @param registrationDate The timestamp when the user registered.
     * @param lastModified The timestamp when the user's details were last modified.
     */
    public User(int id, String username, String password, String email, String role, String avatarUrl, Timestamp registrationDate, Timestamp lastModified) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.avatarUrl = avatarUrl;
        this.registrationDate = registrationDate;
        this.lastModified = lastModified;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier of the user.
     * 
     * @return The unique identifier of the user.
     */
    public int getId() { 
        return id; 
    }

    /**
     * Sets the unique identifier of the user.
     * 
     * @param id The unique identifier of the user.
     */
    public void setId(int id) { 
        this.id = id; 
    }

    /**
     * Gets the username of the user.
     * 
     * @return The username of the user.
     */
    public String getUsername() { 
        return username; 
    }

    /**
     * Sets the username of the user.
     * 
     * @param username The username of the user.
     */
    public void setUsername(String username) { 
        this.username = username; 
    }

    /**
     * Gets the password of the user.
     * 
     * @return The password of the user.
     */
    public String getPassword() { 
        return password; 
    }

    /**
     * Sets the password of the user.
     * 
     * @param password The password of the user.
     */
    public void setPassword(String password) { 
        this.password = password; 
    }

    /**
     * Gets the email address of the user.
     * 
     * @return The email address of the user.
     */
    public String getEmail() { 
        return email; 
    }

    /**
     * Sets the email address of the user.
     * 
     * @param email The email address of the user.
     */
    public void setEmail(String email) { 
        this.email = email; 
    }

    /**
     * Gets the role of the user.
     * 
     * @return The role of the user.
     */
    public String getRole() { 
        return role; 
    }

    /**
     * Sets the role of the user.
     * 
     * @param role The role of the user.
     */
    public void setRole(String role) { 
        this.role = role; 
    }

    /**
     * Gets the URL of the user's avatar.
     * 
     * @return The URL of the user's avatar.
     */
    public String getAvatarUrl() { 
        return avatarUrl; 
    }

    /**
     * Sets the URL of the user's avatar.
     * 
     * @param avatarUrl The URL of the user's avatar.
     */
    public void setAvatarUrl(String avatarUrl) { 
        this.avatarUrl = avatarUrl; 
    }

    /**
     * Gets the timestamp when the user registered.
     * 
     * @return The timestamp when the user registered.
     */
    public Timestamp getRegistrationDate() { 
        return registrationDate; 
    }

    /**
     * Sets the timestamp when the user registered.
     * 
     * @param registrationDate The timestamp when the user registered.
     */
    public void setRegistrationDate(Timestamp registrationDate) { 
        this.registrationDate = registrationDate; 
    }

    /**
     * Gets the timestamp when the user's details were last modified.
     * 
     * @return The timestamp when the user's details were last modified.
     */
    public Timestamp getLastModified() { 
        return lastModified; 
    }

    /**
     * Sets the timestamp when the user's details were last modified.
     * 
     * @param lastModified The timestamp when the user's details were last modified.
     */
    public void setLastModified(Timestamp lastModified) { 
        this.lastModified = lastModified; 
    }
}
