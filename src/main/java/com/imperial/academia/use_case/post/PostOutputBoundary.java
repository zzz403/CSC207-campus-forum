package com.imperial.academia.use_case.post;

import java.util.List;

public interface PostOutputBoundary {
    /**
     * Initializes the post page display with the given post information data.
     *
     * @param postInfoData the data required to initialize the post page
     */
    void initPostPage(PostInfoData postInfoData);

    /**
     * Adds a like to the post with the given post ID.
     *
     * @param postId the ID of the post to add a like to
     */
    void addLike(int postId);

    /**
     * Removes a like from the post with the given post ID.
     *
     * @param postId the ID of the post to remove a like from
     */
    void removeLike(int postId);

    /**
     * Initializes the comments section with the given comments.
     *
     * @param comments the comments to initialize the comments section with
     */
    void initComments(List<CommentData> comments);

    /**
     * Adds a comment to the post.
     *
     * @param commentData the comment to add
     */
    void addPost(CommentData commentData);
}
