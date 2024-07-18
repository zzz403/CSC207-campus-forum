package com.imperial.academia.interface_adapter.post;

import java.sql.Timestamp;

import com.imperial.academia.use_case.post.PostInfoData;
import com.imperial.academia.use_case.post.PostOutputBoundary;

/**
 * The PostPresenter class handles the presentation logic for a post.
 * It updates the PostViewModel with the data from the PostInfoData.
 */
public class PostPresenter implements PostOutputBoundary {
    private final PostViewModel postViewModel;

    /**
     * Constructs a new PostPresenter with the specified PostViewModel.
     * 
     * @param postViewModel the ViewModel to be updated with post data.
     */
    public PostPresenter(PostViewModel postViewModel) {
        this.postViewModel = postViewModel;
    }

    /**
     * Initializes the post page with the given post information data.
     * 
     * @param postInfoData the data required to initialize the post page.
     */
    @Override
    public void initPostPage(PostInfoData postInfoData) {
        String title = postInfoData.getTitle();
        String content = postInfoData.getContent();
        String username = postInfoData.getUsername();
        String avatarUrl = postInfoData.getAvatarUrl();
        Timestamp date = postInfoData.getDate();
        
        postViewModel.setStateTitle(title);
        postViewModel.setStateContent(content);
        postViewModel.setStateUsername(username);
        postViewModel.setStateAvatarUrl(avatarUrl);
        postViewModel.setStateDate(date);
    }
}
