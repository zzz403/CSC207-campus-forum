package com.imperial.academia.app.usecase_factory;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.interface_adapter.createpost.CreatePostController;
import com.imperial.academia.interface_adapter.createpost.CreatePostPresenter;
import com.imperial.academia.interface_adapter.createpost.CreatePostViewModel;
import com.imperial.academia.service.BoardService;
import com.imperial.academia.use_case.createpost.CreatePostInputBoundary;
import com.imperial.academia.use_case.createpost.CreatePostInteractor;
import com.imperial.academia.view.CreatePostView;

public class CreatePostUseCaseFactory {
    /** Prevent instantiation. */
    private CreatePostUseCaseFactory(){}

    public static CreatePostView create(ViewManagerModel viewManagerModel, CreatePostViewModel createPostViewModel) throws ClassNotFoundException{
        CreatePostController createPostController = createController(viewManagerModel, createPostViewModel);
        return new CreatePostView(createPostController,createPostViewModel);
    }

    private static CreatePostController createController(ViewManagerModel viewManagerModel, CreatePostViewModel createPostViewModel){
        CreatePostPresenter createPostPresenter = new CreatePostPresenter(viewManagerModel, createPostViewModel);
        BoardService boardService = ServiceFactory.getBoardService();
        CreatePostInputBoundary createPostInteractor = new CreatePostInteractor(createPostPresenter,boardService);
        return new CreatePostController(createPostInteractor);
    }
}
