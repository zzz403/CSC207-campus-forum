package com.imperial.academia.interface_adapter.createpost;

import java.util.List;

import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.use_case.createpost.CreatePostOutputBoundary;

public class CreatePostPresenter implements CreatePostOutputBoundary{

    private final ViewManagerModel viewManagerModel;
    private final CreatePostViewModel createPostViewModel;

    public CreatePostPresenter(ViewManagerModel viewManagerModel, CreatePostViewModel createPostViewModel){
        this.viewManagerModel = viewManagerModel;
        this.createPostViewModel = createPostViewModel;
    }

    @Override
    public void updateBoardsName(List<String> boardsName){
        CreatePostState createPostState = createPostViewModel.getState();
        createPostState.setBoardName(boardsName);
        createPostViewModel.setState(createPostState);
    }
}
