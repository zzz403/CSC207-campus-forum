package com.imperial.academia.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.imperial.academia.interface_adapter.postboard.PostBoardController;
import com.imperial.academia.interface_adapter.postboard.PostBoardViewModel;

/**
 * This class represents the view for the Post Board in the application.
 * It extends JPanel and sets up the layout and appearance of the Post Board view.
 */
public class PostBoardView extends JPanel {
    /** The name of this view. */
    public final String viewName = "post board";
    
    /** The main panel that holds the components of this view. */
    private JPanel mainPanel;

    // private final PostBoardViewModel posterViewModel;
    // private final PostBoardController posterController;

    /**
     * Constructs a new PostBoardView with the specified view model and controller.
     * 
     * @param posterViewModel the view model associated with the post board
     * @param posterController the controller associated with the post board
     */
    public PostBoardView(PostBoardViewModel posterViewModel, PostBoardController posterController) {
        // this.posterViewModel = posterViewModel;
        // this.posterController = posterController;

        setLayout(new BorderLayout());

        // Create the main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Add main panel to the center of the layout
        add(mainPanel, BorderLayout.CENTER);
    }
}
