package com.imperial.academia.interface_adapter.signup;

public class SignupState {
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    private String repeatPassword = "";
    private String repeatPasswordError = null;
    private String email = "";
    private String emailError = null;

    public SignupState(SignupState copy) {
        this.username = copy.username;
        this.usernameError = copy.usernameError;
        this.password = copy.password;
        this.passwordError = copy.passwordError;
        this.repeatPassword = copy.repeatPassword;
        this.repeatPasswordError = copy.repeatPasswordError;
        this.email = copy.email;
        this.emailError = copy.emailError;
    }

    public SignupState() {}

    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getRepeatPasswordError() {
        return repeatPasswordError;
    }

    public String getEmail() {
        return email;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public void setRepeatPasswordError(String repeatPasswordError) {
        this.repeatPasswordError = repeatPasswordError;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }
}
