package com.imperial.academia.use_case.post;

/**
 * The PostInputBoundary interface defines the input boundary for the Post use
 * case.
 */
public interface PostInputBoundary {

    /**
     * Initializes the post page desplay with the given post information data.
     * 
     * @param postInfoData the data required to initialize the post page.
     */
    void initPostPage(PostInfoData postInfoData);

    /**
     * Initializes the post page with the post information for the post with the
     * given post ID.
     * 
     * @param postID the ID of the post to initialize the post page with.
     * @return true if the post was fetched successfully, false otherwise.
     */
    Boolean initPostById(int postID);

    /**
     * Adds a like to the post with the given post ID.
     * 
     * @param postId the ID of the post to add a like to.
     * @param userId the ID of the user adding the like.
     * @return true if the like was added successfully, false otherwise.
     */
    boolean addLike(int postId, int userId);

    /**
     * Removes a like from the post with the given post ID.
     * 
     * @param postId the ID of the post to remove a like from.
     * @param userId the ID of the user removing the like.
     * @return true if the like was removed successfully, false otherwise.
     */
    boolean removeLike(int postId, int userId);

    /**
     * Checks if the post with the given post ID is liked by the user with the given
     * user ID.
     * 
     * @param postId the ID of the post to check if liked.
     * @param userId the ID of the user to check if liked.
     * @return true if the post is liked by the user, false otherwise.
     */
    boolean checkLiked(int postId, int userId);
}