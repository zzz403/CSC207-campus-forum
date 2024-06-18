package com.imperial.academia.app;

import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.imperial.academia.interface_adapter.login.LoginViewModel;
import com.imperial.academia.interface_adapter.signup.SignupViewModel;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.view.LoginView;
import com.imperial.academia.view.SignupView;
import com.imperial.academia.view.ForumView;
import com.imperial.academia.view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        // Set the look and feel to JTattoo
        try {
            Properties props = new Properties();
            props.put("logoString", "MyApp"); // Customize the logo text
            AluminiumLookAndFeel.setCurrentTheme(props);
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Add a window listener to handle the close operation
        application.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
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

        application.pack();
        application.setVisible(true);
    }
}
