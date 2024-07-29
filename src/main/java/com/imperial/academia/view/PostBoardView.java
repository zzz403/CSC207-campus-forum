package com.imperial.academia.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

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

    /**
     * Constructs a new PostBoardView with the specified view model and controller.
     * 
     * @param posterViewModel the view model associated with the post board
     */
    public PostBoardView(PostBoardViewModel posterViewModel) {
        // this.posterViewModel = posterViewModel;

        setLayout(new BorderLayout());

        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTHWEST;

        // 使用工厂类创建多个 PostSmallComponent 实例并添加到主面板
        String imageUrl1 = "resources/test_image/test_image_1.jpg";
        String imageUrl2 = "resources/test_image/test_image_2.jpg";
        // String avatarUrl = "resources/avatar/avatarExample.png";

        postBoardController.fetchAllPost();

        List<PostOverviewInfo> postInfoList = posterViewModel.getPostInfoList();

        for (int i = 0; i < postInfoList.size(); i++) { // 例如创建8个组件
            PostOverviewInfo pInfo = postInfoList.get(i);
            int postID = pInfo.getPostID();
            String title = pInfo.getPostTitle();
            String summary = pInfo.getSummary();
            String username = pInfo.getUserName();
            String avatarURL = pInfo.getAvatarURL();
            int postLikes = pInfo.getLikes();
            PostSmallComponent postComponent = PostSmallComponentFactory.createPostSmallComponent(
                    (i % 2 == 0) ? imageUrl1 : imageUrl2,
                    avatarURL, // user avatar url
                    title, // post title
                    summary, // summary content
                    username, // author
                    postLikes // likes
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
        // mainPanel.removeAll();

        // 创建一个 JScrollPane 并将 mainPanel 添加到其中
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Add scroll panel to the center of the layout
        add(scrollPane, BorderLayout.CENTER);
    }
}
