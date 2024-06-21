package com.imperial.academia.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.imperial.academia.interface_adapter.postboard.PostBoardController;
import com.imperial.academia.interface_adapter.postboard.PostBoardPresenter;
import com.imperial.academia.interface_adapter.postboard.PostBoardViewModel;

public class PostBoardView extends JPanel {
    public final String viewName = "post board";

    public PostBoardView(PostBoardViewModel posterViewModel, PostBoardController posterController, PostBoardPresenter posterPresenter) {
        setLayout(new BorderLayout());

        // Create and add the top navigation bar
        TopNavigationBar topNavigationBar = new TopNavigationBar();
        add(topNavigationBar, BorderLayout.NORTH);

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Add main panel to the center of the layout
        add(mainPanel, BorderLayout.CENTER);
    }
}
