package com.imperial.academia.app.usecase_factory;

import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.interface_adapter.postboard.CreatePostController;
import com.imperial.academia.interface_adapter.postboard.CreatePostViewModel;
import com.imperial.academia.view.CreatePostView;

public class CreatePostUseCaseFactory {
    /** Prevent instantiation. */
    private CreatePostUseCaseFactory(){}

    public static CreatePostView create(ViewManagerModel viewManagerModel, CreatePostViewModel createPostViewModel) throws ClassNotFoundException{
        CreatePostController createPostController = new CreatePostController();
        // CreatePostPresenter createPostPresenter = new CreatePostPresenter();
        return new CreatePostView(createPostController,createPostViewModel);
    }
}
