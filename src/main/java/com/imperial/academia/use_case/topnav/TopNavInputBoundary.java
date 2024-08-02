package com.imperial.academia.use_case.topnav;

/**
 * TopNavInputBoundary
 */
public interface TopNavInputBoundary {

    /**
     * Search for a post by its title.
     * 
     * @param title The title of the post to search for.
     */
    void searchPostByTitle(String title);
    
}
