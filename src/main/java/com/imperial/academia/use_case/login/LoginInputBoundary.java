package com.imperial.academia.use_case.login;

import java.io.IOException;

public interface LoginInputBoundary {
    void execute(LoginInputData loginInputData);
    String[] loadCredentials() throws IOException;
    void navigateToSignup();
}