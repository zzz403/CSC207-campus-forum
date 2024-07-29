package com.imperial.academia.use_case.postBoard;

/**
 * The PostBoardInputBoundary interface defines the input boundary for the
 * PostBoard use case.
 */
public interface PostBoardInputBoundary {
    /**
     * Fetches all posts from the database and updates the post board view with the
     * post information.
     * 
     * @return true if the posts were fetched successfully, false otherwise.
     */
    Boolean fetchAllPost();
}
