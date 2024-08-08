package com.imperial.academia.use_case.postBoard;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.entity.post.Post;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.service.PostService;
import com.imperial.academia.service.UserService;
import com.imperial.academia.session.SessionManager;
import com.imperial.academia.use_case.post.PostOverviewInfo;

/**
 * The PostBoardInteractor class handles the interaction logic for the post
 * board.
 * It acts as a mediator between the input boundary and the output boundary.
 */
public class PostBoardInteractor implements PostBoardInputBoundary {

    // The output boundary that will handle the presentation logic.
    private final PostBoardOutputBoundary postBoardPresenter;
    // The service for posts.
    private final PostService postService;
    // The service for users.
    private final UserService userService;

    /**
     * Constructs a new PostBoardInteractor with the specified
     * PostBoardOutputBoundary.
     * 
     * @param postBoardPresenter the output boundary that will handle the
     *                           presentation logic.
     */
    public PostBoardInteractor(PostBoardOutputBoundary postBoardPresenter) {
        this.postBoardPresenter = postBoardPresenter;
        this.postService = ServiceFactory.getPostService();
        this.userService = ServiceFactory.getUserService();
    }

    /**
     * Constructs a new PostBoardInteractor with the specified
     * PostBoardOutputBoundary, PostService, UserService, and LLMInputBoundary.
     * 
     * @param postBoardPresenter the output boundary that will handle the
     *                           presentation
     *                           logic.
     * @param postService        the service that will handle the post logic.
     * @param userService        the service that will handle the user logic.
     * @param llmInteractor      the interactor that will handle the LLM logic.
     */
    public PostBoardInteractor(PostBoardOutputBoundary postBoardPresenter, PostService postService,
            UserService userService) {
        this.postBoardPresenter = postBoardPresenter;
        this.postService = postService;
        this.userService = userService;
    }

    /**
     * Fetches all posts from the database and updates the post board view with the
     * post information.
     * 
     * @return true if the posts were fetched successfully, false otherwise.
     */
    @Override
    public Boolean fetchAllPost() {
        List<Post> posts;
        try {
            posts = postService.getAll();
        } catch (SQLException e) {
            System.out.println("fetch all post unseccess");
            return false;
        }
        List<PostOverviewInfo> postInfoList = new ArrayList<>();
        for (Post post : posts) {
            PostOverviewInfo postInfoData = getPostInfoData(post, false);
            postInfoList.add(postInfoData);
        }
        postBoardPresenter.updatePostList(postInfoList);
        return true;
    }

    /**
     * Fetches all posts from the database and updates the post board view with the
     * post information.
     * 
     * @return true if the posts were fetched successfully, false otherwise.
     */
    private PostOverviewInfo getPostInfoData(Post post, boolean isLiked) {
        int postID = post.getId();
        int postLikes = 0;
        String title = post.getTitle();
        String content = post.getContent();
        // String summary = llmInteractor.summarizeChatHistory(content);
        String username = "404not found";
        String avatarURL = "resources\\avatar\\default_avatar.png";
        try {
            int userID = post.getAuthorId();
            User user = userService.get(userID);
            avatarURL = user.getAvatarUrl();
            username = user.getUsername();
            postLikes = postService.getTotalLikesNumberByPostId(postID);
        } catch (SQLException e) {
            System.out.println("no user found for this post");
        }
        
        PostOverviewInfo postInfoData = PostOverviewInfo.builder()
                .setPostID(postID)
                .setLikes(postLikes)
                .setPostTitle(title)
                .setSummary(content)
                .setUserName(username)
                .setAvatarURL(avatarURL)
                .setIsLiked(isLiked)
                .build();
        return postInfoData;
    }

    /**
     * Updates the isLiked status of all posts in the post board view.
     */
    public void updateisLiked(){
        List<Post> posts;
        try {
            posts = postService.getAll();
        } catch (SQLException e) {
            System.out.println("fetch all post unseccess");
            return;
        }
        User currUser = SessionManager.getCurrentUser();
        for (Post post : posts) {
            boolean isLiked;
            try {
                isLiked = postService.checkLiked(post.getId(), currUser.getId());
            } catch (SQLException e) {
                isLiked = false;
            }
            postBoardPresenter.updateIsLikeByPostId(post.getId(), isLiked);
        }
    }
}
