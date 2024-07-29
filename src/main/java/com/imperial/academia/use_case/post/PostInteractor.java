package com.imperial.academia.use_case.post;

import java.sql.SQLException;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.entity.post.Post;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.service.PostService;
import com.imperial.academia.service.UserService;

import java.sql.Timestamp;

/**
 * The PostInteractor class handles the interaction logic for posts.
 * It acts as a mediator between the input boundary and the output boundary.
 */
public class PostInteractor implements PostInputBoundary {
    private final PostOutputBoundary postPresenter;

    private final PostService postService;

    private final UserService userService;

    /**
     * Constructs a new PostInteractor with the specified PostOutputBoundary.
     * 
     * @param postPresenter the output boundary that will handle the presentation logic.
     */
    public PostInteractor(PostOutputBoundary postPresenter) {
        this.postPresenter = postPresenter;
        this.postService = ServiceFactory.getPostService();
        this.userService = ServiceFactory.getUserService();
    }

    /**
     * Initializes the post page desplay with the given post information data.
     * 
     * @param postInfoData the data required to initialize the post page.
     */
    @Override
    public void initPostPage(PostInfoData postInfoData) {
        postPresenter.initPostPage(postInfoData);
    }

    /**
     * Initializes the post page with the post information for the post with the
     * given post ID.
     * 
     * @param postID the ID of the post to initialize the post page with.
     * @return true if the post was fetched successfully, false otherwise.
     */
    @Override
    public Boolean initPostById(int postID) {
        try {
            Post post = postService.get(postID);
            int userID = post.getAuthorId();
            User user = userService.get(userID);

            String title = post.getTitle();
            String content = post.getContent();
            String username = user.getUsername();
            String avatarUrl = user.getAvatarUrl();
            Timestamp date = post.getLastModifiedDate();
            int postLikes = postService.getTotalLikesNumberByPostId(postID);
            
            PostInfoData postInfoData = PostInfoData.builder()
                                                    .setTitle(title)
                                                    .setContent(content)
                                                    .setUsername(username)
                                                    .setAvatarUrl(avatarUrl)
                                                    .setDate(date)
                                                    .setLikes(postLikes)
                                                    .build();

            postPresenter.initPostPage(postInfoData);
        } catch (SQLException e) {
            System.out.println("Can't find the post or user DNE when init post page");
            return false;
        }
        return true;
    }
}
