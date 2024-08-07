package com.imperial.academia.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import com.imperial.academia.interface_adapter.post.PostController;
import com.imperial.academia.interface_adapter.post.PostViewModel;
import com.imperial.academia.use_case.post.CommentData;
import com.imperial.academia.view.style.CustomScrollBarUI;

/**
 * The PostView class represents the view for a single post in the application.
 * It includes a header, content area, and a comment section.
 */
public class PostView extends JPanel {

    // The name of this view.
    public final String viewName = "post";

    // The ViewModel associated with this view.
    private final PostViewModel postViewModel;

    // The controller for the post view.
    private final PostController postController;

    // The main frame of the application.
    private final JFrame applicationFrame;

    /**
     * Constructor for the PostView class.
     * 
     * @param postViewModel the ViewModel associated with this view.
     */
    public PostView(PostViewModel postViewModel, JFrame applicationFrame) {
        this.postViewModel = postViewModel;
        this.postController = new PostController();
        this.applicationFrame = applicationFrame;

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
        JScrollPane commentsScrollPane = new CustomScrollBarUI.CustomScrollPane(commentsArea);
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
        postCommentButton.addActionListener(e -> postComment(commentInput));

        commentInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    postComment(commentInput);
                }
            }
        });

        postViewModel.addPropertyChangeListener(evt -> {
            if (evt.getPropertyName().equals("comments")) {
                commentsArea.setText("");
                List<CommentData> commentDatas = postViewModel.getStateComments();
                for (CommentData commentData : commentDatas) {
                    displayComment(commentsArea, commentData);
                }
            } else if (evt.getPropertyName().equals("addComment")) {
                List<CommentData> commentDatas = postViewModel.getStateComments();
                CommentData newComment = commentDatas.get(commentDatas.size() - 1);
                displayComment(commentsArea, newComment);
            }
        });

        JPanel commentInputPanel = new JPanel(new BorderLayout());
        commentInputPanel.add(commentInput, BorderLayout.CENTER);
        commentInputPanel.add(postCommentButton, BorderLayout.EAST);

        commentSection.add(commentInputPanel, BorderLayout.CENTER);
        return commentSection;
    }

    private void displayComment(JTextArea commentsArea, CommentData commentData) {
        String userName = commentData.getUsername();
        String content = commentData.getContent().strip();
        Timestamp time = commentData.getLastModified();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(time);
        commentsArea.append(date + "\n");
        commentsArea.append(userName + ": " + content + "\n");
    }

    /**
     * Posts a comment to the post.
     */
    private void postComment(JTextField commentInput) {
        String commentText = commentInput.getText().strip();
        if (commentText.isBlank()) {
            return;
        }
        int postId = postViewModel.getStatePostId();
        postController.postComment(postId, commentText);
        commentInput.setText("");
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
        JScrollPane contentScrollPane = new CustomScrollBarUI.CustomScrollPane(contentArea);
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

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(255, 255, 255));

        // Title label
        JLabel titleLabel = new JLabel("Test test 123123");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel likePanel = getLikePanel();

        titlePanel.add(likePanel, BorderLayout.EAST);
        titlePanel.add(titleLabel, BorderLayout.WEST);

        headerPanel.add(userInfoPanel, BorderLayout.CENTER);
        headerPanel.add(titlePanel, BorderLayout.NORTH);

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

    /**
     * Creates and returns the like panel.
     * 
     * @return the JPanel representing the like button.
     */
    private JPanel getLikePanel() {
        JPanel likePanel = new JPanel(new BorderLayout());
        likePanel.setLayout(new BoxLayout(likePanel, BoxLayout.X_AXIS));
        likePanel.setOpaque(false);
        likePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Load and scale like icon
        Image likeIcon = getLikeIcon(postViewModel.getStateIsLiked());

        if (likeIcon != null) {
            JLabel likeIconLabel = new JLabel(new ImageIcon(likeIcon));
            likeIconLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5)); // Add some padding
            likePanel.add(likeIconLabel);

            postViewModel.addPropertyChangeListener(evt -> {
                if (evt.getPropertyName().equals("isLiked")) {
                    likeIconLabel.setIcon(
                        new ImageIcon(Objects.requireNonNull(getLikeIcon(postViewModel.getStateIsLiked())))
                    );
                }
            });
        }

        JLabel likesLabel = new JLabel(String.valueOf(postViewModel.getStateLikes()));
        likesLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        likePanel.add(likesLabel);

        likePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                postController.likeClick(postViewModel.getStatePostId());
            }
        });

        postViewModel.addPropertyChangeListener(evt -> {
            if (evt.getPropertyName().equals("postLikes")) {
                likesLabel.setText(String.valueOf(postViewModel.getStateLikes()));
            }
        });

        // fix display issue when size changing
        applicationFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                likesLabel.setVisible(false);
                likesLabel.setVisible(true);
            }
        });
        return likePanel;
    }

    /**
     * Loads and scales the like icon.
     * 
     * @param isLiked whether the post is liked.
     * @return the scaled like icon.
     */
    private Image getLikeIcon(boolean isLiked) {
        BufferedImage likeIcon;
        try {
            if (!isLiked) {
                likeIcon = ImageIO.read(new File("resources/icons/like_icon.png"));
            } else {
                likeIcon = ImageIO.read(new File("resources/icons/like_icon_red.png"));
            }
        } catch (IOException e) {
            return null;
        }
        return likeIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    }
}
