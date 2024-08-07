package com.imperial.academia.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.imperial.academia.app.components_factory.PostSmallComponentFactory;
import com.imperial.academia.interface_adapter.postboard.PostBoardController;
import com.imperial.academia.interface_adapter.postboard.PostBoardViewModel;
import com.imperial.academia.use_case.post.PostOverviewInfo;
import com.imperial.academia.view.components.PostSmallComponent;
import com.imperial.academia.view.style.CustomScrollBarUI;

/**
 * This class represents the view for the Post Board in the application.
 * It extends JPanel and sets up the layout and appearance of the Post Board
 * view.
 */
public class PostBoardView extends JPanel {
    /** The name of this view. */
    public final String viewName = "post board";

    /** The main panel that holds the components of this view. */
    private JPanel mainPanel;

    /** The controller for the post board view. */
    private final PostBoardController postBoardController;

    private JFrame applicationFrame;

    private final Map<Integer, PostSmallComponent> postCaChe = new HashMap<>();

    private static final int POST_COMPONENT_WIDTH = 325;
    private int postNum = 3;

    /**
     * Constructs a new PostBoardView with the specified view model.
     * 
     * @param postBoardViewModel the view model associated with the post board
     */
    public PostBoardView(PostBoardViewModel postBoardViewModel, JFrame applicationFrame) {
        this.postBoardController = new PostBoardController();
        this.applicationFrame = applicationFrame;
        setLayout(new BorderLayout());

        // Initialize the main panel before adding to JScrollPane
        mainPanel = new JPanel(new GridBagLayout());

        JScrollPane scrollPane = new CustomScrollBarUI.CustomScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        postBoardViewModel.addPropertyChangeListener(evt -> {
            if (evt.getPropertyName().equals("addPost")) {
                System.out.println("PostBoardView: property changed");
                mainPanel.removeAll();
                generatePostComponents(postBoardViewModel);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        // Fetch posts after setting up the view
        postBoardController.fetchAllPost();
        generatePostComponents(postBoardViewModel);

        // Add scroll panel to the center of the layout

        applicationFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustPostNumBasedOnWidth();
                generatePostComponents(postBoardViewModel);
            }
        });

        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Generates the post components for the post board view.
     * 
     * @param postBoardViewModel the view model associated with the post board
     */
    private void generatePostComponents(PostBoardViewModel postBoardViewModel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTHWEST;

        List<PostOverviewInfo> postInfoList = postBoardViewModel.getPostInfoList();

        for (int i = 0; i < postInfoList.size(); i++) { // 例如创建8个组件
            PostOverviewInfo pInfo = postInfoList.get(i);
            int postID = pInfo.getPostID();
            String title = pInfo.getPostTitle();
            String summary = pInfo.getSummary();
            String username = pInfo.getUserName();
            String avatarURL = pInfo.getAvatarURL();
            int postLikes = pInfo.getLikes();
            Random rand = new Random();
            PostSmallComponent postComponent = null;
            if (postCaChe.get(postID) != null) {
                postComponent = postCaChe.get(postID);
                gbc.gridx = i % postNum;
                gbc.gridy = i / postNum;
                mainPanel.add(postComponent, gbc);
                continue;
            }

            int randomNum = rand.nextInt((20 - 1) + 1) + 1;
            if (i < 19) {
                randomNum = i + 1;
            }
            postComponent = PostSmallComponentFactory.createPostSmallComponent(
                    "resources/test_image/test_image_" + randomNum + ".jpg",
                    avatarURL, // user avatar url
                    title, // post title
                    summary, // summary content
                    username, // author
                    postLikes, // likes
                    applicationFrame);

            postComponent.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    if (postBoardController.initPostById(postID)) {
                        System.out.println("init post success | postID: " + postID);
                        return;
                    }
                    System.out.println("init post unsuccess | postID: " + postID);
                }
            });

            PostSmallComponent finalPostComponent = postComponent;
            postBoardViewModel.addPropertyChangeListener(evt -> {
                if (evt.getPropertyName().equals("likeChangeInc=" + postID)) {
                    int likes = postBoardViewModel.getPostLikesByPostId(postID);
                    finalPostComponent.setLikes(likes);
                } else if (evt.getPropertyName().equals("likeChangeDec=" + postID)) {
                    int likes = postBoardViewModel.getPostLikesByPostId(postID);
                    finalPostComponent.setLikes(likes);
                } else if(evt.getPropertyName().equals("isLikedChange=" + postID)) {
                    boolean isLike = postBoardViewModel.getIsLikeByPostId(postID);
                    finalPostComponent.setLikeIcon(isLike);
                }
            });

            gbc.gridx = i % postNum;
            gbc.gridy = i / postNum;

            postCaChe.put(postID, postComponent);
            mainPanel.add(postComponent, gbc);
        }
    }

    /**
     * Adjusts the number of posts per row based on the width of the application
     * frame.
     */
    private void adjustPostNumBasedOnWidth() {
        int frameWidth = applicationFrame.getWidth();
        int newPostNum = Math.max(1, frameWidth / POST_COMPONENT_WIDTH); // Ensure at least 1 post per row

        if (newPostNum != postNum) {
            postNum = newPostNum;
            System.out.println("Adjusting posts per row to: " + postNum);
        }
    }
}
