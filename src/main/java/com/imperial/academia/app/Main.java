package com.imperial.academia.app;

import com.imperial.academia.interface_adapter.chat.ChatSideBarViewModel;
import com.imperial.academia.interface_adapter.chat.ChatWindowViewModel;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.interface_adapter.createpost.CreatePostViewModel;
import com.imperial.academia.interface_adapter.login.LoginViewModel;
import com.imperial.academia.interface_adapter.post.PostViewModel;
import com.imperial.academia.interface_adapter.postboard.PostBoardViewModel;
import com.imperial.academia.interface_adapter.profile.ProfileViewModel;
import com.imperial.academia.interface_adapter.signup.SignupViewModel;
import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarViewModel;
import com.imperial.academia.view.*;
import com.imperial.academia.view.components.ChatSideBarView;
import com.imperial.academia.view.components.ChatWindowView;
import com.imperial.academia.view.components.TopNavigationBar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * The main entry point of the Academia Imperial application.
 * This class sets up the main application window and initializes the various views and view models.
 */
public class Main {
    public static void main(String[] args) throws SQLException, IOException {

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

        // Set the application icon logo
        try {
            Image logo = ImageIO.read(new File("resources/logo.png"));
            application.setIconImage(logo);

            // Set macOS Dock icon if running on macOS
            if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                Taskbar.getTaskbar().setIconImage(logo);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load logo image");
        }

        // Set the size of the main application window and center it on the screen
        application.setSize(1000, 700);
        application.setLocationRelativeTo(null);

        // Use CardLayout to manage different views in the application
        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // Initialize ViewManagerModel
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // Initialize ViewModels
        ViewModels viewModels = new ViewModels();

        LoginViewModel loginViewModel = viewModels.getLoginViewModel();
        SignupViewModel signupViewModel = viewModels.getSignupViewModel();
        PostBoardViewModel postBoardViewModel = viewModels.getPostBoardViewModel();
        CreatePostViewModel createPostViewModel = viewModels.getCreatePostViewModel();
        ChatSideBarViewModel chatSideBarViewModel = viewModels.getChatSideBarViewModel();
        ChatWindowViewModel chatWindowViewModel = viewModels.getChatWindowViewModel();
        TopNavigationBarViewModel topNavigationBarViewModel = viewModels.getTopNavigationBarViewModel();
        ProfileViewModel profileViewModel = viewModels.getProfileViewModel();
        PostViewModel postViewModel = viewModels.getPostViewModel();

        try {
            // Initialize services and use cases
            ServiceFactory.initialize();
            UsecaseFactory.initialize(viewManagerModel, viewModels);

            // Create views and add them to the card layout
            SignupView signupView = new SignupView(signupViewModel);
            views.add(signupView, signupView.viewName);

            LoginView loginView = new LoginView(loginViewModel);
            views.add(loginView, loginView.viewName);

            PostBoardView postBoardView = new PostBoardView(postBoardViewModel);
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

            // Add the top navigation bar to the views
            createPostView.add(new TopNavigationBar(topNavigationBarViewModel, application), BorderLayout.NORTH);
            postBoardView.add(new TopNavigationBar(topNavigationBarViewModel, application), BorderLayout.NORTH);
            chatView.add(new TopNavigationBar(topNavigationBarViewModel, application), BorderLayout.NORTH);
            profileView.add(new TopNavigationBar(topNavigationBarViewModel, application), BorderLayout.NORTH);
            postView.add(new TopNavigationBar(topNavigationBarViewModel, application), BorderLayout.NORTH);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Set the initial view to "log in"
        viewManagerModel.setActiveView("log in");
        // viewManagerModel.setActiveView("post");
        viewManagerModel.firePropertyChanged();

        // Make the main application window visible
        application.setVisible(true);
    }
}
