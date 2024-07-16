package com.imperial.academia.use_case.createpost;

import java.util.List;

/**
 * This interface represents the output boundary for the create post use case.
 * It defines the method required to update the view model with the list of board names.
 */
public interface CreatePostOutputBoundary {

    /**
     * Updates the view model with the list of board names.
     * 
     * @param boardNames the list of board names to update in the view model
     */
    void updateBoardsName(List<String> boardNames);

    /**
     * Submit post seccuess, and reset the view model
     */
    void submitSeccuss();

    /**
     * Updates the content to the create post view model
     */
    void updateContent(String content);
}
