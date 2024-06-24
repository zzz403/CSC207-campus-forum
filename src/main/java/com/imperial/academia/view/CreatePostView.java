package com.imperial.academia.view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.imperial.academia.interface_adapter.postboard.CreatePostController;
import com.imperial.academia.interface_adapter.postboard.CreatePostViewModel;

public class CreatePostView extends JPanel {
    public final String viewName = "create post";

    public CreatePostView(CreatePostController createPostController, CreatePostViewModel createPostViewModel) {
        setLayout(new BorderLayout());
        
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
        
        // Tags Panel
        JPanel tagsPanel = new JPanel();
        JTextField tagsField = new JTextField(20);
        tagsField.setBorder(BorderFactory.createTitledBorder("Tags"));
        tagsPanel.add(tagsField);
        
        // Buttons Panel
        JPanel buttonsPanel = new JPanel();
        JButton saveDraftButton = new JButton("Save Draft");
        JButton postButton = new JButton("Post");
        buttonsPanel.add(saveDraftButton);
        buttonsPanel.add(postButton);
        
        // Add panels to main panel
        add(titlePanel, BorderLayout.NORTH);
        add(bodyPanel, BorderLayout.CENTER);
        add(tagsPanel, BorderLayout.SOUTH);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
}
