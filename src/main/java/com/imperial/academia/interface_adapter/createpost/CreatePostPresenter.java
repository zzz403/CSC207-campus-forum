package com.imperial.academia.interface_adapter.createpost;

import java.util.List;

import com.imperial.academia.use_case.createpost.CreatePostOutputBoundary;

/**
 * The presenter that handles updating the view model with the board names
 * for the create post use case.
 */
public class CreatePostPresenter implements CreatePostOutputBoundary {
    
    /** The view model associated with creating a post. */
    private final CreatePostViewModel createPostViewModel;

    /**
     * Constructs a new CreatePostPresenter with the specified view manager model and create post view model.
     * 
     * @param createPostViewModel the view model associated with creating a post
     */
    public CreatePostPresenter(CreatePostViewModel createPostViewModel) {
        this.createPostViewModel = createPostViewModel;
    }

    /**
     * Updates the view model with the list of board names.
     * 
     * @param boardsName the list of board names to update in the view model
     */
    @Override
    public void updateBoardsName(List<String> boardsName) {
        CreatePostState createPostState = createPostViewModel.getState();
        createPostState.setBoardsName(boardsName);
        createPostViewModel.setState(createPostState);
    }

    /**
     * Submit post seccuess, change the view to postboard
     */
    @Override
    public void submitSeccuss(){
        createPostViewModel.resetState();
    }

    @Override
    public void updateContent(String content) {
        createPostViewModel.setStateContent(content);
    }
}
