package com.imperial.academia.use_case.signup;

public class SignupOutputData {
    private final String username;
    private String creationTime;

    public SignupOutputData(String username, String creationTime) {
        this.username = username;
        this.creationTime = creationTime;
    }

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