package com.imperial.academia.use_case.signup;

/**
 * Data transfer object for the signup input data.
 */
public class SignupInputData {
    private final String username;
    private final String password;
    private final String repeatPassword;
    private final String email;

    /**
     * Constructs a SignupInputData with the given data.
     *
     * @param username the username
     * @param password the password
     * @param repeatPassword the repeated password for confirmation
     * @param email the email address
     */
    public SignupInputData(String username, String password, String repeatPassword, String email) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.email = email;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getEmail() {
        return email;
    }
}
