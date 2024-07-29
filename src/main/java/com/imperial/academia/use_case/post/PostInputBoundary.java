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
}