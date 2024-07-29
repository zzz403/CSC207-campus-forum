package com.imperial.academia.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.imperial.academia.app.components_factory.PostSmallComponentFactory;
import com.imperial.academia.interface_adapter.postboard.PostBoardController;
import com.imperial.academia.interface_adapter.postboard.PostBoardViewModel;
import com.imperial.academia.use_case.post.PostOverviewInfo;
import com.imperial.academia.view.components.PostSmallComponent;

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
    private final PostBoardController postBoardController = new PostBoardController();

    private JFrame applicationFrame;
    /**
     * Constructs a new PostBoardView with the specified view model.
     * 
     * @param postBoardViewModel the view model associated with the post board
     */
    public PostBoardView(PostBoardViewModel postBoardViewModel, JFrame applicationFrame) {
        this.applicationFrame = applicationFrame;
        setLayout(new BorderLayout());

        // Initialize the main panel before adding to JScrollPane
        mainPanel = new JPanel(new GridBagLayout());

        JScrollPane scrollPane = new JScrollPane(mainPanel);
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
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Generates the post components for the post board view.
     * 
     * @param posterViewModel the view model associated with the post board
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
            int randomNum = rand.nextInt((9 - 1) + 1) + 1;

            PostSmallComponent postComponent = PostSmallComponentFactory.createPostSmallComponent(
                    "resources/test_image/test_image_"+randomNum+".jpg",
                    avatarURL, // user avatar url
                    title, // post title
                    summary, // summary content
                    username, // author
                    postLikes, // likes
                    applicationFrame
            );

            postComponent.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    if (postBoardController.initPostById(postID)) {
                        System.out.println("init post success | postID: " + postID);
                        return;
                    }
                    System.out.println("init post unsuccess | postID: " + postID);
                }
            });

            gbc.gridx = i % 3;
            gbc.gridy = i / 3;
            mainPanel.add(postComponent, gbc);
        }
    }
}
