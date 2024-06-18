package com.imperial.academia.use_case.login;

public class LoginOutputData {
    private final String username;
    private final String message;

    public LoginOutputData(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }
}
