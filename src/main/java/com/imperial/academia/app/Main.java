package com.imperial.academia.app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.imperial.academia.app.usecase_factory.ChatUseCaseFactory;
import com.imperial.academia.app.usecase_factory.CreatePostUseCaseFactory;
import com.imperial.academia.app.usecase_factory.LoginUseCaseFactory;
import com.imperial.academia.app.usecase_factory.PostBoardUseCaseFactory;
import com.imperial.academia.app.usecase_factory.SignupUseCaseFactory;
import com.imperial.academia.app.usecase_factory.TopNavigationBarUseCaseFacory;
import com.imperial.academia.interface_adapter.chat.ChatSideBarViewModel;
import com.imperial.academia.interface_adapter.chat.ChatWindowViewModel;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.interface_adapter.login.LoginViewModel;
import com.imperial.academia.interface_adapter.postboard.CreatePostViewModel;
import com.imperial.academia.interface_adapter.postboard.PostBoardViewModel;
import com.imperial.academia.interface_adapter.signup.SignupViewModel;
import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarViewModel;
import com.imperial.academia.view.ChatView;
import com.imperial.academia.view.CreatePostView;
import com.imperial.academia.view.ForumView;
import com.imperial.academia.view.LoginView;
import com.imperial.academia.view.PostBoardView;
import com.imperial.academia.view.SignupView;
import com.imperial.academia.view.ViewManager;

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

        // icon logo
        try {
            Image logo = ImageIO.read(new File("resources/logo.png"));
            application.setIconImage(logo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        application.setSize(1100, 900); // 设置窗口大小
        application.setLocationRelativeTo(null); // 居中显示

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        PostBoardViewModel postBoardViewModel = new PostBoardViewModel();
        CreatePostViewModel createPostViewModel = new CreatePostViewModel();
        ChatSideBarViewModel chatSideBarViewModel = new ChatSideBarViewModel();
        ChatWindowViewModel chatWindowViewModel = new ChatWindowViewModel();
        TopNavigationBarViewModel topNavigationBarViewModel = new TopNavigationBarViewModel();

        try {
            ServiceFactory.initialize();

            SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel);
            views.add(signupView, signupView.viewName);

            LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel,topNavigationBarViewModel);
            views.add(loginView, loginView.viewName);

            PostBoardView postBoardView = PostBoardUseCaseFactory.create(viewManagerModel, postBoardViewModel);
            views.add(postBoardView, postBoardView.viewName);

            CreatePostView createPostView = CreatePostUseCaseFactory.create(viewManagerModel, createPostViewModel);
            views.add(createPostView, createPostView.viewName);

            ChatView chatView = ChatUseCaseFactory.create(viewManagerModel, chatSideBarViewModel, chatWindowViewModel);
            views.add(chatView, chatView.viewName);

            // Add the top navigation bar to the post board view
            // postBoardView.addTopNavigationBar(topNavigationBar);
            
            createPostView.add(TopNavigationBarUseCaseFacory.create(viewManagerModel, topNavigationBarViewModel,application), BorderLayout.NORTH);
            postBoardView.add(TopNavigationBarUseCaseFacory.create(viewManagerModel, topNavigationBarViewModel,application), BorderLayout.NORTH);
            chatView.add(TopNavigationBarUseCaseFacory.create(viewManagerModel, topNavigationBarViewModel,application), BorderLayout.NORTH);
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ForumView forumView = new ForumView();

        views.add(forumView, forumView.viewName);

        // Set the initial view to "log in"
        viewManagerModel.setActiveView("log in");
        viewManagerModel.firePropertyChanged();

        // Set size and center the window
        
        application.setVisible(true);

    }
}
