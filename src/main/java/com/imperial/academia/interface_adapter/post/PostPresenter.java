package com.imperial.academia.interface_adapter.post;

import java.sql.Timestamp;
import java.util.List;

import com.imperial.academia.interface_adapter.postboard.PostBoardViewModel;
import com.imperial.academia.use_case.post.CommentData;
import com.imperial.academia.use_case.post.PostInfoData;
import com.imperial.academia.use_case.post.PostOutputBoundary;

/**
 * The PostPresenter class handles the presentation logic for a post.
 * It updates the PostViewModel with the data from the PostInfoData.
 */
public class PostPresenter implements PostOutputBoundary {
    private final PostViewModel postViewModel;

    private final PostBoardViewModel postBoardViewModel;

    /**
     * Constructs a new PostPresenter with the specified PostViewModel.
     * 
     * @param postViewModel the ViewModel to be updated with post data.
     */
    public PostPresenter(PostViewModel postViewModel, PostBoardViewModel postBoardViewModel) {
        this.postViewModel = postViewModel;
        this.postBoardViewModel = postBoardViewModel;
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
        int likes = postInfoData.getLikes();
        int postId = postInfoData.getPostID();
        boolean isLiked = postInfoData.isLiked();

        postViewModel.setStateTitle(title);
        postViewModel.setStateContent(content);
        postViewModel.setStateUsername(username);
        postViewModel.setStateAvatarUrl(avatarUrl);
        postViewModel.setStateDate(date);
        postViewModel.setStateLikes(likes);
        postViewModel.setStatePostID(postId);
        postViewModel.setStateIsLiked(isLiked);
    }

    /**
     * Adds a like to the post with the given post ID.
     * 
     * @param postId the ID of the post to add a like to.
     */
    @Override
    public void addLike(int postId) {
        postViewModel.incrementStateLikes();
        postViewModel.setStateIsLiked(true);
        postBoardViewModel.incrementStateLikesByPostId(postId);
    }

    /**
     * Removes a like from the post with the given post ID.
     * 
     * @param postId the ID of the post to remove a like from.
     */
    @Override
    public void removeLike(int postId) {
        postViewModel.decrementStateLikes();
        postViewModel.setStateIsLiked(false);
        postBoardViewModel.decrementStateLikesByPostId(postId);
    }

    /**
     * Initializes the comments section with the given comments.
     * 
     * @param comments the comments to initialize the comments section with.
     */
    @Override
    public void initComments(List<CommentData> comments) {
        postViewModel.setStateComments(comments);
    }

    /**
     * Adds a comment to the post.
     * 
     * @param commentData the comment to add.
     */
    @Override
    public void addPost(CommentData commentData) {
        postViewModel.addStateComment(commentData);
    }
}
