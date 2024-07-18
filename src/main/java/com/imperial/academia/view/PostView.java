package com.imperial.academia.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import com.imperial.academia.interface_adapter.post.PostViewModel;

/**
 * The PostView class represents the view for a single post in the application.
 * It includes a header, content area, and a comment section.
 */
public class PostView extends JPanel {

    public final String viewName = "post";

    private final PostViewModel postViewModel;

    /**
     * Constructor for the PostView class.
     * 
     * @param postViewModel the ViewModel associated with this view.
     */
    public PostView(PostViewModel postViewModel) {
        this.postViewModel = postViewModel;

        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        JPanel headerPanel = getHeaderPanel();
        JPanel contentPanel = getContentPanel();
        JPanel commentSection = getCommentPanel();

        // Add panels to main layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(245, 245, 245));

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(commentSection, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Creates and returns the comment section panel.
     * 
     * @return the JPanel representing the comment section.
     */
    private JPanel getCommentPanel() {
        // Comment Section
        JPanel commentSection = new JPanel(new BorderLayout());
        commentSection.setBorder(new EmptyBorder(10, 10, 10, 10));
        commentSection.setBackground(new Color(255, 255, 255));

        // Comments display area
        JTextArea commentsArea = new JTextArea();
        commentsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        commentsArea.setEditable(false);
        JScrollPane commentsScrollPane = new JScrollPane(commentsArea);
        commentsScrollPane.setPreferredSize(new Dimension(400, 150));
        commentsScrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        commentSection.add(commentsScrollPane, BorderLayout.SOUTH);

        // Comment input area
        JTextField commentInput = new JTextField();
        commentInput.setFont(new Font("Arial", Font.PLAIN, 14));
        commentInput.setPreferredSize(new Dimension(200, 60));
        commentInput.setBorder(BorderFactory.createTitledBorder("Add comment"));
        JButton postCommentButton = new JButton("Post Comment");
        postCommentButton.setFont(new Font("Arial", Font.PLAIN, 14));
        postCommentButton.setPreferredSize(new Dimension(200, 50));
        postCommentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String commentText = commentInput.getText();
                
                if (!commentText.trim().isEmpty()) {
                    commentsArea.append("Me: " + commentText + "\n"); // TODO: replace by save it to db
                    commentInput.setText("");
                }
            }
        });

        JPanel commentInputPanel = new JPanel(new BorderLayout());
        commentInputPanel.add(commentInput, BorderLayout.CENTER);
        commentInputPanel.add(postCommentButton, BorderLayout.EAST);

        commentSection.add(commentInputPanel, BorderLayout.CENTER);
        return commentSection;
    }

    /**
     * Creates and returns the content panel.
     * 
     * @return the JPanel representing the content area.
     */
    private JPanel getContentPanel() {
        // Content Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.setBackground(new Color(255, 255, 255));

        JTextPane contentArea = new JTextPane();
        contentArea.setText("test content 123123LSAJKD DSLAKJ H");
        contentArea.setFont(new Font("Arial", Font.PLAIN, 14));
        contentArea.setEditable(false);
        contentArea.setBackground(new Color(255, 255, 255));
        JScrollPane contentScrollPane = new JScrollPane(contentArea);
        contentScrollPane.setPreferredSize(new Dimension(400, 200));
        contentScrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        postViewModel.addPropertyChangeListener(evt -> {
            if (evt.getPropertyName().equals("postContent")) {
                contentArea.setText(postViewModel.getStateContent());
            }
        });
        contentPanel.add(contentScrollPane, BorderLayout.CENTER);
        return contentPanel;
    }

    /**
     * Creates and returns the header panel.
     * 
     * @return the JPanel representing the header.
     */
    private JPanel getHeaderPanel() {
        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        headerPanel.setBackground(new Color(255, 255, 255));

        // User info panel
        JPanel userInfoPanel = new JPanel(new BorderLayout());
        userInfoPanel.setBackground(new Color(255, 255, 255));
        // Username
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        // create time
        JLabel createTime = new JLabel("2024-07-17");
        createTime.setFont(new Font("Arial", Font.PLAIN, 12));
        userInfoPanel.add(usernameLabel, BorderLayout.CENTER);
        userInfoPanel.add(createTime, BorderLayout.EAST);

        // Title label
        JLabel titleLabel = new JLabel("Test test 123123");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        headerPanel.add(userInfoPanel, BorderLayout.CENTER);
        headerPanel.add(titleLabel, BorderLayout.NORTH);

        // property listener
        postViewModel.addPropertyChangeListener(evt -> {
            if (evt.getPropertyName().equals("postUsername")) {
                usernameLabel.setText(postViewModel.getStateUsername());
            } else if (evt.getPropertyName().equals("postDate")) {
                String date = postViewModel.getFormattedStateDate();
                createTime.setText(date);
            } else if (evt.getPropertyName().equals("postTitle")) {
                titleLabel.setText(postViewModel.getStateTitle());
            }
        });
        return headerPanel;
    }
}
