package com.imperial.academia.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.imperial.academia.interface_adapter.createpost.CreatePostController;
import com.imperial.academia.interface_adapter.createpost.CreatePostViewModel;

import java.awt.GridLayout;

/**
 * This class represents the view for creating a post in the application.
 * It extends JPanel and sets up the layout and appearance of the Create Post view.
 */
public class CreatePostView extends JPanel {

    /** The name of this view. */
    public final String viewName = "create post";

    /** The controller associated with creating a post. */
    private final CreatePostController createPostController;
    
    /** The view model associated with creating a post. */
    private final CreatePostViewModel createPostViewModel;

    /**
     * Constructs a new CreatePostView with the specified controller and view model.
     * 
     * @param createPostController the controller associated with creating a post
     * @param createPostViewModel the view model associated with creating a post
     */
    public CreatePostView(CreatePostController createPostController, CreatePostViewModel createPostViewModel) {
        this.createPostController = createPostController;
        this.createPostViewModel = createPostViewModel;

        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        JTextField titleField = new JTextField();
        titleField.setBorder(BorderFactory.createTitledBorder("Title"));
        titlePanel.add(titleField, BorderLayout.CENTER);

        // Body Panel
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BorderLayout());
        JTextArea bodyArea = new JTextArea();
        bodyArea.setBorder(BorderFactory.createTitledBorder("Body"));
        bodyPanel.add(new JScrollPane(bodyArea), BorderLayout.CENTER);

        // Drop down box Panel
        JPanel boardSelectPanel = new JPanel();
        boardSelectPanel.setLayout(new BorderLayout());

        JComboBox<String> boardSelect = new JComboBox<>(getBoardsName());
        boardSelect.setBorder(BorderFactory.createTitledBorder("Select a Board"));
        boardSelect.addActionListener(new ActionListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (e.getSource() instanceof JComboBox) {
                        JComboBox selected = (JComboBox) e.getSource();
                        String boardName = (String) selected.getSelectedItem();
                        System.out.println("selected: " + boardName);
                    } else {
                        throw new IllegalArgumentException("Source is not a boardName");
                    }
                } catch (IllegalArgumentException ex) {
                    System.err.println("An error occurred: " + ex.getMessage());
                }
            }
        });

        boardSelectPanel.add(boardSelect, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel();
        JButton saveDraftButton = new JButton("Save Draft");
        JButton postButton = new JButton("Post");
        buttonsPanel.add(saveDraftButton);
        buttonsPanel.add(postButton);

        // Combine boardSelectPanel and buttonsPanel into one panel
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2, 1));
        southPanel.add(boardSelectPanel);
        southPanel.add(buttonsPanel);

        // Add panels to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(bodyPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Retrieves the names of the boards from the controller and view model.
     * 
     * @return an array of board names
     */
    private String[] getBoardsName() {
        try {
            createPostController.updateBoardName();
        } catch (SQLException e) {
            System.out.println("No board name are find, error from CreatePostView");
        }
        List<String> boardsNameArr = createPostViewModel.getState().getBoardName();
        String[] boardsName = new String[boardsNameArr.size()];
        boardsName = boardsNameArr.toArray(boardsName);
        return boardsName;
    }
}
