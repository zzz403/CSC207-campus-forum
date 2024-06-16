package com.imperial.academia.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private PanelController panelController;

    public MainFrame() {
        setTitle("Academia Imperial");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        panelController = new PanelController(mainPanel, cardLayout);

        LoginPanel loginPanel = new LoginPanel(panelController);
        ForumPanel forumPanel = new ForumPanel();
        SignupPanel signUpPanel = new SignupPanel(panelController);

        mainPanel.add(loginPanel, "login");
        mainPanel.add(forumPanel, "forum");
        mainPanel.add(signUpPanel, "register");

        add(mainPanel);

        panelController.showPanel("login");
    }
}
