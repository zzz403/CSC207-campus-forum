// com/imperial/academia/app/SignupUseCaseFactory.java
package com.imperial.academia.app;

import com.imperial.academia.data_access.DatabaseConnection;
import com.imperial.academia.data_access.UserDAO;
import com.imperial.academia.entity.user.CommonUserFactory;
import com.imperial.academia.entity.user.UserFactory;
import com.imperial.academia.interface_adapter.signup.SignupController;
import com.imperial.academia.interface_adapter.signup.SignupPresenter;
import com.imperial.academia.interface_adapter.signup.SignupViewModel;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.interface_adapter.login.LoginViewModel;
import com.imperial.academia.use_case.signup.SignupInputBoundary;
import com.imperial.academia.use_case.signup.SignupInteractor;
import com.imperial.academia.use_case.signup.SignupOutputBoundary;
import com.imperial.academia.view.SignupView;

import javax.swing.*;
import java.sql.SQLException;

public class SignupUseCaseFactory {

    /** Prevent instantiation. */
    private SignupUseCaseFactory() {}

    public static SignupView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel) throws ClassNotFoundException {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel);
            return new SignupView(signupController, signupViewModel);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel) throws SQLException, ClassNotFoundException {
        UserDAO userDataAccessObject = new UserDAO(DatabaseConnection.getConnection());

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }
}
