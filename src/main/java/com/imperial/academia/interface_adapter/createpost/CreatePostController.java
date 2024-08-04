package com.imperial.academia.interface_adapter.createpost;

import java.sql.SQLException;

import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.use_case.createpost.CreatePostInputBoundary;

/**
 * The controller that handles the update board name action for the create post
 * use case.
 */
public class CreatePostController {

    /** The interactor that handles the business logic for creating a post. */
    private final CreatePostInputBoundary createPostInteractor;

    /**
     * Constructs a new CreatePostController with the specified interactor.
     * 
     */
    public CreatePostController() {
        this.createPostInteractor = UsecaseFactory.getCreatePostInteractor();
    }

    /**
     * Constructs a new CreatePostController with the specified interactor.
     * 
     * @param createPostInteractor the interactor that handles the business logic
     *                             for creating a post.
     */
    public CreatePostController(CreatePostInputBoundary createPostInteractor) {
        this.createPostInteractor = createPostInteractor;
    }

    /**
     * Updates the list of board names by delegating to the interactor.
     * 
     * @throws SQLException if a database access error occurs
     */
    public void updateBoardName() throws SQLException {
        createPostInteractor.updateBoardsName();
    }

    /**
     * Submit the post
     * 
     * @param title     title of the post
     * @param content   content of the post
     * @param boardName which board name this post is create in
     * @return true if submit post success, otherwise false
     */
    public boolean submitPost(String title, String content, String boardName) {
        return createPostInteractor.submitPost(title, content, boardName);
    }

    /**
     * Enhance the content using ChatGPT
     * 
     * @param content the content to enhance
     */
    public void enhanceContentUsingChatGPT(String content) {
        createPostInteractor.enhanceContent(content);
    }
}
