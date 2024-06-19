package com.imperial.academia.app;

import com.imperial.academia.interface_adapter.login.LoginViewModel;
import com.imperial.academia.interface_adapter.signup.SignupViewModel;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.view.LoginView;
import com.imperial.academia.view.SignupView;
import com.imperial.academia.view.ForumView;
import com.imperial.academia.view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) {
        // The main application window.
        JFrame application = new JFrame("Academia Imperial");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Add a window listener to handle the close operation
        application.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        // icon logo
        try {
            Image logo = ImageIO.read(new File("resources/logo.png"));
            application.setIconImage(logo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);
        
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        
        try {
            SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel);
            views.add(signupView, signupView.viewName);

            LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel);
            views.add(loginView, loginView.viewName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ForumView forumView = new ForumView();
        views.add(forumView, forumView.viewName);

        // Set the initial view to "log in"
        viewManagerModel.setActiveView("log in");
        viewManagerModel.firePropertyChanged();

        // Set size and center the window
        application.setSize(800, 600); // 设置窗口大小
        application.setLocationRelativeTo(null); // 居中显示
        application.setVisible(true);
    }
}
