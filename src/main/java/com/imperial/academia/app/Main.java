package com.imperial.academia.app;

import com.imperial.academia.interface_adapter.chat.*;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.interface_adapter.createpost.CreatePostViewModel;
import com.imperial.academia.interface_adapter.edit.EditViewModel;
import com.imperial.academia.interface_adapter.login.LoginViewModel;
import com.imperial.academia.interface_adapter.post.*;
import com.imperial.academia.interface_adapter.postboard.PostBoardViewModel;
import com.imperial.academia.interface_adapter.profile.ProfileViewModel;
import com.imperial.academia.interface_adapter.signup.SignupViewModel;
import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarViewModel;
import com.imperial.academia.view.*;
import com.imperial.academia.view.components.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Main class acts as the entry point for the Academia Imperial application.
 * It configures the main application window, initializes view models, and manages views.
 */
public class Main {
    public static void main(String[] args) throws SQLException, IOException {

        // Main application frame setup
        JFrame application = new JFrame("Academia Imperial");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Window listener for application close event
        application.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        // Loading and setting application icon
        try {
            Image logo = ImageIO.read(new File("resources/logo.png"));
            application.setIconImage(logo);
            // Special handling for macOS Dock icon
            if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                Taskbar.getTaskbar().setIconImage(logo);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load logo image");
        }

        // Configure application window size and centering
        application.setSize(1000, 700);
        application.setLocationRelativeTo(null);

        // CardLayout to manage different views
        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // View manager initialization
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // ViewModel initialization
        ViewModels viewModels = new ViewModels();

        // Setting up ViewModels for different application components
        LoginViewModel loginViewModel = viewModels.getLoginViewModel();
        SignupViewModel signupViewModel = viewModels.getSignupViewModel();
        PostBoardViewModel postBoardViewModel = viewModels.getPostBoardViewModel();
        CreatePostViewModel createPostViewModel = viewModels.getCreatePostViewModel();
        ChatSideBarViewModel chatSideBarViewModel = viewModels.getChatSideBarViewModel();
        ChatWindowViewModel chatWindowViewModel = viewModels.getChatWindowViewModel();
        TopNavigationBarViewModel topNavigationBarViewModel = viewModels.getTopNavigationBarViewModel();
        ProfileViewModel profileViewModel = viewModels.getProfileViewModel();
        PostViewModel postViewModel = viewModels.getPostViewModel();
        EditViewModel editViewModel = viewModels.getEditViewModel();

        try {
            // Initialize backend services and use cases
            ServiceFactory.initialize();
            UsecaseFactory.initialize(viewManagerModel, viewModels);

            // Creating views and integrating them into the application
            SignupView signupView = new SignupView(signupViewModel);
            views.add(signupView, signupView.viewName);

            LoginView loginView = new LoginView(loginViewModel);
            views.add(loginView, loginView.viewName);

            PostBoardView postBoardView = new PostBoardView(postBoardViewModel, application);
            views.add(postBoardView, postBoardView.viewName);

            CreatePostView createPostView = new CreatePostView(createPostViewModel);
            views.add(createPostView, createPostView.viewName);

            ChatSideBarView chatSideBarView = new ChatSideBarView(chatSideBarViewModel);
            ChatWindowView chatWindowView = new ChatWindowView(chatWindowViewModel, application);
            ChatView chatView = new ChatView(chatSideBarView, chatWindowView);
            views.add(chatView, chatView.viewName);

            ProfileView profileView = new ProfileView(profileViewModel);
            views.add(profileView, profileView.viewName);

            PostView postView = new PostView(postViewModel);
            views.add(postView, postView.viewName);

            EditView editView = new EditView(editViewModel, application);
            views.add(editView, editView.viewName);

            // Integrating top navigation bar with views
            createPostView.add(new TopNavigationBar(topNavigationBarViewModel, application), BorderLayout.NORTH);
            postBoardView.add(new TopNavigationBar(topNavigationBarViewModel, application), BorderLayout.NORTH);
            chatView.add(new TopNavigationBar(topNavigationBarViewModel, application), BorderLayout.NORTH);
            profileView.add(new TopNavigationBar(topNavigationBarViewModel, application), BorderLayout.NORTH);
            postView.add(new TopNavigationBar(topNavigationBarViewModel, application), BorderLayout.NORTH);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Set initial view and trigger view update
        viewManagerModel.setActiveView("log in");
        viewManagerModel.firePropertyChanged();

        // Display the main application window
        application.setVisible(true);
    }

}
