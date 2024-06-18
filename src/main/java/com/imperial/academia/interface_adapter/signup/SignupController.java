// com/imperial/academia/interface_adapter/signup/SignupController.java
package com.imperial.academia.interface_adapter.signup;

import com.imperial.academia.use_case.signup.SignupInputBoundary;
import com.imperial.academia.use_case.signup.SignupInputData;

public class SignupController {
    final SignupInputBoundary signupInteractor;

    public SignupController(SignupInputBoundary signupInteractor) {
        this.signupInteractor = signupInteractor;
    }

    public void execute(String username, String password1, String password2, String email) {
        SignupInputData signupInputData = new SignupInputData(username, password1, password2, email);
        signupInteractor.execute(signupInputData);
    }
}
