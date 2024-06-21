package com.imperial.academia.app;

import com.imperial.academia.data_access.remember_me.RememberMeDAO;
import com.imperial.academia.interface_adapter.login.LoginController;
import com.imperial.academia.interface_adapter.login.LoginPresenter;
import com.imperial.academia.interface_adapter.login.LoginViewModel;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.service.UserService;
import com.imperial.academia.use_case.login.LoginInputBoundary;
import com.imperial.academia.use_case.login.LoginInteractor;
import com.imperial.academia.use_case.login.LoginOutputBoundary;
import com.imperial.academia.view.LoginView;

import javax.swing.*;
import java.sql.SQLException;

public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) throws ClassNotFoundException {

        try {
            LoginController loginController = createUserLoginUseCase(viewManagerModel, loginViewModel);
            return new LoginView(loginController, loginViewModel);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createUserLoginUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) throws SQLException, ClassNotFoundException {
        UserService userService = ServiceFactory.getUserService();

        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loginViewModel);

        RememberMeDAO rememberMeDAO = new RememberMeDAO();

        LoginInputBoundary userLoginInteractor = new LoginInteractor(
                userService, loginOutputBoundary, rememberMeDAO);

        return new LoginController(userLoginInteractor);
    }
}
