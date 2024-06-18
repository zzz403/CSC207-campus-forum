package com.imperial.academia.use_case.signup;

public interface SignupOutputBoundary {
    void prepareSuccessView(SignupOutputData signupOutputData);
    void prepareFailView(String error);
}
