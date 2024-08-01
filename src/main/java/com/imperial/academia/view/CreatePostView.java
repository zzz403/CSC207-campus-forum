package com.imperial.academia.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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

/**
 * This class represents the view for creating a post in the application.
 * It extends JPanel and sets up the layout and appearance of the Create Post
 * view.
 */
public class CreatePostView extends JPanel {

    /** The name of this view. */
    public final String viewName = "create post";

    /** The controller associated with creating a post. */
    private final CreatePostController createPostController = new CreatePostController();

    /** The view model associated with creating a post. */
    private final CreatePostViewModel createPostViewModel;

    /**
     * Constructs a new CreatePostView with the specified controller and view model.
     * 
     * @param createPostViewModel  the view model associated with creating a post
     */
    public CreatePostView(CreatePostViewModel createPostViewModel) {
        this.createPostViewModel = createPostViewModel;

        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        JTextField titleField = new JTextField(createPostViewModel.getStateTitle());
        titleField.setBorder(BorderFactory.createTitledBorder("Title"));
        titlePanel.add(titleField, BorderLayout.CENTER);

        // content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        JTextArea contentArea = new JTextArea(createPostViewModel.getStateContent());
        contentArea.setBorder(BorderFactory.createTitledBorder("Content"));
        contentPanel.add(new JScrollPane(contentArea), BorderLayout.CENTER);

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

                        createPostViewModel.setStateCurrentBoardName(boardName);

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

        // save Draft Button
        JButton saveDraftButton = new JButton("Save Draft");
        saveDraftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String title = titleField.getText().strip();
                String content = contentArea.getText().strip();
                
                createPostViewModel.setStateTitle(title);
                createPostViewModel.setStateContent(content);

                System.out.println("Save post success");
            }
        });
        
        // post button
        JButton postButton = new JButton("Post");
        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText().strip();
                String content = contentArea.getText().strip();
                String boardName = createPostViewModel.getStateCurrentBoardName().strip();
                if (title.equals("") || content.equals("")) {
                    System.out.println("Post submit unseccuss due to illegal/empty input for title or content");
                    return;
                }
                System.out.println("Post submit | Title: " + title + " | Content: " + content + " | BoardName: " + boardName);
                createPostController.submitPost(title, content, boardName);
            }
        });
            
        // enhance content button
        JButton enhanceContentButton = new JButton("Enhance Content");
            
        enhanceContentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content = contentArea.getText();
                if (content.strip().isEmpty()) {
                    System.out.println("Content is empty, cannot enhance.");
                    return;
                }
                
                // Call the method to enhance content using ChatGPT
                createPostController.enhanceContentUsingChatGPT(content);
            }
        });

        // Add property change listener to update the view when the model changes
        createPostViewModel.addPropertyChangeListener(evt -> {
            if("content".equals(evt.getPropertyName())){
                contentArea.setText(createPostViewModel.getStateContent());
            }else if("title".equals(evt.getPropertyName())){
                titleField.setText(createPostViewModel.getStateTitle());
            }else if("reset".equals(evt.getPropertyName())){
                titleField.setText(createPostViewModel.getStateTitle());
                contentArea.setText(createPostViewModel.getStateContent());
                boardSelect.setSelectedIndex(0);
            }
        });

        // add button to the buttonsPanel
        buttonsPanel.add(enhanceContentButton);
        buttonsPanel.add(saveDraftButton);
        buttonsPanel.add(postButton);
        
        // Combine boardSelectPanel and buttonsPanel into one panel
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2, 1));
        southPanel.add(boardSelectPanel);
        southPanel.add(buttonsPanel);

        // Add panels to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
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
        List<String> boardsNameArr = createPostViewModel.getState().getBoardsName();
        System.out.println("Boards Name: " + boardsNameArr);
        String[] boardsName = new String[boardsNameArr.size()];
        boardsName = boardsNameArr.toArray(boardsName);
        createPostViewModel.setStateCurrentBoardName(boardsName[0]);
        return boardsName;
    }
}
