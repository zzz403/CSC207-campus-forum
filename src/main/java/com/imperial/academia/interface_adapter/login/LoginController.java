package com.imperial.academia.interface_adapter.login;

import java.io.IOException;

import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.use_case.login.LoginInputBoundary;
import com.imperial.academia.use_case.login.LoginInputData;

/**
 * The LoginController class handles the login process and manages
 * the interaction between the view and the login use case.
 */
public class LoginController {
    final LoginInputBoundary loginInteractor;

    /**
     * Constructs a LoginController with the specified LoginInputBoundary.
     *
     * @param loginInteractor The login interactor to handle login logic.
     */
    public LoginController(LoginInputBoundary loginInteractor) {
        this.loginInteractor = loginInteractor;
    }

    /**
     * Constructs a LoginController using the UsecaseFactory.
     */
    public LoginController() {
        this(UsecaseFactory.getLoginInteractor());
    }

    /**
     * Executes the login process with the provided credentials.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @param rememberMe Whether to remember the user.
     */
    public void execute(String username, String password, boolean rememberMe) {
        LoginInputData loginInputData = new LoginInputData(username, password, rememberMe);
        loginInteractor.execute(loginInputData);
    }

    /**
     * Loads the saved credentials if available.
     *
     * @return An array containing the username and password.
     */
    public String[] loadCredentials() {
        try {
            return loginInteractor.loadCredentials();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[2];
    }

    /**
     * Navigates to the signup view.
     */
    public void navigateToSignup() {
        loginInteractor.navigateToSignup();
    }
}
