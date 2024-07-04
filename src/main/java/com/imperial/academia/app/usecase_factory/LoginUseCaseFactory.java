package com.imperial.academia.app.usecase_factory;

import com.imperial.academia.interface_adapter.login.LoginController;
import com.imperial.academia.interface_adapter.login.LoginPresenter;
import com.imperial.academia.interface_adapter.login.LoginViewModel;
import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarViewModel;
import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.data_access.RememberMeDAO;
import com.imperial.academia.interface_adapter.chat.ChatSideBarPresenter;
import com.imperial.academia.interface_adapter.chat.ChatSideBarViewModel;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.service.ChatGroupService;
import com.imperial.academia.service.UserService;
import com.imperial.academia.use_case.chat.ChatSideBarInputBoundary;
import com.imperial.academia.use_case.chat.ChatSideBarInteractor;
import com.imperial.academia.use_case.chat.ChatSideBarOutputBoundary;
import com.imperial.academia.use_case.login.LoginInputBoundary;
import com.imperial.academia.use_case.login.LoginInteractor;
import com.imperial.academia.use_case.login.LoginOutputBoundary;
import com.imperial.academia.view.LoginView;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Factory class for creating the Login use case and its related components.
 */
public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {
    }

    /**
     * Creates and returns a LoginView.
     *
     * @param viewManagerModel the view manager model
     * @param loginViewModel   the login view model
     * @return the login view
     * @throws ClassNotFoundException if the UserDAO class is not found
     */
    public static LoginView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel,
            TopNavigationBarViewModel topNavigationBarViewModel, ChatSideBarViewModel chatSideBarViewModel)
            throws ClassNotFoundException {
        try {
            LoginController loginController = createUserLoginUseCase(viewManagerModel, loginViewModel,
                    topNavigationBarViewModel, chatSideBarViewModel);
            return new LoginView(loginController, loginViewModel);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    /**
     * Creates the LoginController and its dependencies.
     *
     * @param viewManagerModel the view manager model
     * @param loginViewModel   the login view model
     * @return the login controller
     * @throws SQLException           if a database access error occurs
     * @throws ClassNotFoundException if the UserDAO class is not found
     */
    private static LoginController createUserLoginUseCase(ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel, TopNavigationBarViewModel topNavigationBarViewModel,
            ChatSideBarViewModel chatSideBarViewModel) throws SQLException, ClassNotFoundException {
        // Get the UserService instance from the ServiceFactory
        UserService userService = ServiceFactory.getUserService();
        ChatGroupService chatGroupService = ServiceFactory.getChatGroupService();

        // Create the presenter for the login use case
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loginViewModel,
                topNavigationBarViewModel);

        // Create the RememberMeDAO
        RememberMeDAO rememberMeDAO = new RememberMeDAO();

        ChatSideBarOutputBoundary chatSideBarPresenter = new ChatSideBarPresenter(chatSideBarViewModel);
        ChatSideBarInputBoundary chatSideBarInteractor = new ChatSideBarInteractor(chatGroupService,
                chatSideBarPresenter);

        // Create the interactor for the login use case
        LoginInputBoundary userLoginInteractor = new LoginInteractor(
                userService, loginOutputBoundary, rememberMeDAO, chatSideBarInteractor);

        // Return the login controller
        return new LoginController(userLoginInteractor);
    }
}
