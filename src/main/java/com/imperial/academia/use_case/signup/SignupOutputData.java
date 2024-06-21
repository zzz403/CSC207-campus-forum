package com.imperial.academia.use_case.signup;

/**
 * Data transfer object for the signup output data.
 */
public class SignupOutputData {
    private final String username;
    private String creationTime;

    /**
     * Constructs a SignupOutputData with the given data.
     *
     * @param username the username
     * @param creationTime the creation time
     */
    public SignupOutputData(String username, String creationTime) {
        this.username = username;
        this.creationTime = creationTime;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}
