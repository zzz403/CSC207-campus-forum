package com.imperial.academia.use_case.post;

public interface PostInputBoundary {

    /**
     * Initializes the post page desplay with the given post information data.
     * 
     * @param postInfoData the data required to initialize the post page.
     */
    void initPostPage(PostInfoData postInfoData);
}