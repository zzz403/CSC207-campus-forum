package com.imperial.academia.app;

import com.imperial.academia.interface_adapter.login.LoginViewModel;
import com.imperial.academia.interface_adapter.poster.PosterViewModel;
import com.imperial.academia.interface_adapter.signup.SignupViewModel;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.view.LoginView;
import com.imperial.academia.view.PosterView;
import com.imperial.academia.view.SignupView;
import com.imperial.academia.view.ForumView;
import com.imperial.academia.view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;

/**
 * Main class for the Academia Imperial application.
 * This class sets up the main application window and initializes different views.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        // Create the main application window
        JFrame application = new JFrame("Academia Imperial");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Add a window listener to handle the close operation
        application.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        // Set the application icon
        try {
            Image logo = ImageIO.read(new File("resources/logo.png"));
            application.setIconImage(logo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set up a CardLayout for switching between different views
        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // Initialize the ViewManagerModel
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // Initialize the view models for login and signup
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();

        // Initialize the service factory and create the views
        PosterViewModel posterViewModel = new PosterViewModel();

        try {
            ServiceFactory.initialize();
            SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel);
            views.add(signupView, signupView.viewName);

            LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel);
            views.add(loginView, loginView.viewName);

            PosterView posterView = PosterUseCaseFactory.create(viewManagerModel, posterViewModel);
            views.add(posterView, posterView.viewName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Create and add the forum view

        ForumView forumView = new ForumView(viewManagerModel);

        views.add(forumView, forumView.viewName);

        // Set the initial view to "log in"
        viewManagerModel.setActiveView("log in");
        viewManagerModel.firePropertyChanged();

        // Set the size of the application window and center it on the screen
        application.setSize(800, 700);
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}
