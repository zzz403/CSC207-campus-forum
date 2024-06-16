package com.imperial.academia.frontend;

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
        RegisterPanel registerPanel = new RegisterPanel(panelController);

        mainPanel.add(loginPanel, "login");
        mainPanel.add(forumPanel, "forum");
        mainPanel.add(registerPanel, "register");

        add(mainPanel);

        panelController.showPanel("login");
    }
}
