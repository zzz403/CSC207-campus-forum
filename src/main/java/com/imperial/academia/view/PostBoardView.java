package com.imperial.academia.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.imperial.academia.interface_adapter.postboard.PostBoardController;
import com.imperial.academia.interface_adapter.postboard.PostBoardViewModel;
import com.imperial.academia.view.components.TopNavigationBar;

public class PostBoardView extends JPanel {
    public final String viewName = "post board";

    // private final PostBoardViewModel posterViewModel;
    // private final PostBoardController posterController;

    public PostBoardView(PostBoardViewModel posterViewModel, PostBoardController posterController) {
        // this.posterViewModel = posterViewModel;
        // this.posterController = posterController;

        setLayout(new BorderLayout());

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Add main panel to the center of the layout
        add(mainPanel, BorderLayout.CENTER);
    }

    public void addTopNavigationBar(TopNavigationBar topNavigationBar) {
        JPanel topNavPanel = new JPanel();
        topNavPanel.add(topNavigationBar);
        add(topNavPanel);
    }
}
