package com.imperial.academia.app.usecase_factory;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.entity.user.CommonUserFactory;
import com.imperial.academia.entity.user.UserFactory;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.interface_adapter.login.LoginViewModel;
import com.imperial.academia.interface_adapter.signup.SignupController;
import com.imperial.academia.interface_adapter.signup.SignupPresenter;
import com.imperial.academia.interface_adapter.signup.SignupViewModel;
import com.imperial.academia.service.UserService;
import com.imperial.academia.use_case.signup.SignupInputBoundary;
import com.imperial.academia.use_case.signup.SignupInteractor;
import com.imperial.academia.use_case.signup.SignupOutputBoundary;
import com.imperial.academia.view.SignupView;

/**
 * Factory class for creating the Signup use case and its related components.
 */
public class SignupUseCaseFactory {

    /** Prevent instantiation. */
    private SignupUseCaseFactory() {}

    /**
     * Creates and returns a SignupView.
     *
     * @param viewManagerModel the view manager model
     * @param loginViewModel the login view model
     * @param signupViewModel the signup view model
     * @return the signup view
     * @throws ClassNotFoundException if the UserDAO class is not found
     */
    public static SignupView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel) throws ClassNotFoundException {
        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel);
            return new SignupView(signupController, signupViewModel);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    /**
     * Creates the SignupController and its dependencies.
     *
     * @param viewManagerModel the view manager model
     * @param signupViewModel the signup view model
     * @param loginViewModel the login view model
     * @return the signup controller
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the UserDAO class is not found
     */
    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel) throws SQLException, ClassNotFoundException {
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
        UserFactory userFactory = new CommonUserFactory();
        SignupInputBoundary userSignupInteractor = new SignupInteractor(signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }
}
