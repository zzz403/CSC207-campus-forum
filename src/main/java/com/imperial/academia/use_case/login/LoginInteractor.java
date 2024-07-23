package com.imperial.academia.use_case.login;

import com.imperial.academia.session.SessionManager;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.chat.ChatSideBarInputBoundary;
import com.imperial.academia.service.UserService;
import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.data_access.RememberMeDAI;
import com.imperial.academia.entity.user.User;

import java.io.IOException;
import java.sql.SQLException;

/**
 * The LoginInteractor class implements the LoginInputBoundary interface and
 * handles the business logic for the login process.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final UserService userService;
    private final LoginOutputBoundary loginPresenter;
    private final RememberMeDAI rememberMeDAO;
    private final ChatSideBarInputBoundary chatSideBarInteractor;

    private final ChangeViewInputBoundary changeViewInteractor;

    /**
     * Constructs a LoginInteractor with the specified UserService,
     * LoginOutputBoundary, and RememberMeDAI.
     * 
     * @param loginPresenter        The login presenter to present the results.
     * @param rememberMeDAO         The remember me DAO for managing saved
     *                              credentials.
     */
    public LoginInteractor(LoginOutputBoundary loginPresenter, RememberMeDAI rememberMeDAO) {
        this.userService = ServiceFactory.getUserService();
        this.loginPresenter = loginPresenter;
        this.rememberMeDAO = rememberMeDAO;
        this.chatSideBarInteractor = UsecaseFactory.getChatSideBarInteractor();
        this.changeViewInteractor = UsecaseFactory.getChangeViewInteractor();
    }

    public LoginInteractor(LoginOutputBoundary loginPresenter, RememberMeDAI rememberMeDAO, UserService userService, ChatSideBarInputBoundary chatSideBarInteractor, ChangeViewInputBoundary changeViewInteractor) {
        this.userService = userService;
        this.loginPresenter = loginPresenter;
        this.rememberMeDAO = rememberMeDAO;
        this.chatSideBarInteractor = chatSideBarInteractor;
        this.changeViewInteractor = changeViewInteractor;
    }

    /**
     * Executes the login process with the provided login input data.
     * 
     * @param loginInputData The input data for the login process.
     */
    @Override
    public void execute(LoginInputData loginInputData) {
        try {
            loginPresenter.prepareFailView(null);

            User user = userService.getByUsername(loginInputData.getUsername());
            if (user == null) {
                handleLoginFailure("User not found.");
            } else if (!user.getPassword().equals(loginInputData.getPassword())) {
                handleLoginFailure("Invalid password.");
            } else {
                handleLoginSuccess(user, loginInputData.isRememberMe(), loginInputData.getUsername(),
                        loginInputData.getPassword());
            }
        } catch (SQLException e) {
            handleLoginFailure("An error occurred during login: " + e.getMessage());
        }
    }

    private void handleLoginFailure(String errorMessage) {
        loginPresenter.prepareFailView(errorMessage);
    }

    private void handleLoginSuccess(User user, boolean rememberMe, String username, String password) {
        if (rememberMe) {
            try {
                saveCredentials(username, password);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                clearCredentials();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        SessionManager.setCurrentUser(user);
        LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(), "Login successful.",
                user.getAvatarUrl(), user.getId());

        chatSideBarInteractor.execute();
        loginPresenter.prepareSuccessView(loginOutputData);
        changeViewInteractor.changeView("post board");
    }

    /**
     * Saves the provided credentials for future logins.
     * 
     * @param username The username to be saved.
     * @param password The password to be saved.
     * @throws IOException If an I/O error occurs while saving the credentials.
     */
    public void saveCredentials(String username, String password) throws IOException {
        rememberMeDAO.saveCredentials(username, password);
    }

    /**
     * Loads the saved credentials, if available.
     * 
     * @return An array containing the username and password.
     * @throws IOException If an I/O error occurs while loading the credentials.
     */
    public String[] loadCredentials() throws IOException {
        return rememberMeDAO.loadCredentials();
    }

    /**
     * Clears the saved credentials.
     * 
     * @throws IOException If an I/O error occurs while clearing the credentials.
     */
    public void clearCredentials() throws IOException {
        rememberMeDAO.clearCredentials();
    }

    /**
     * Navigates to the signup view.
     */
    @Override
    public void navigateToSignup() {
        changeViewInteractor.changeView("sign up");
    }
}
