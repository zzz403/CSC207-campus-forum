package com.imperial.academia.use_case.post;

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
}
