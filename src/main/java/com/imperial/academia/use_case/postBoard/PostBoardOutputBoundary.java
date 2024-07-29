package com.imperial.academia.use_case.postBoard;

import java.util.List;

import com.imperial.academia.use_case.post.PostOverviewInfo;

/**
 * The PostBoardOutputBoundary interface defines the output boundary for the
 * PostBoard use case.
 */
public interface PostBoardOutputBoundary {
    /**
     * Updates the post list with the given list of post overview information.
     * 
     * @param postsInfo the list of post overview information to update the post
     *                  list with.
     */
    void updatePostList(List<PostOverviewInfo> postsInfo);

    /**
     * Adds a post to the post list.
     * 
     * @param postOverviewInfo the post overview information to add to the post list.
     */
    void addPost(PostOverviewInfo postOverviewInfo);
}
