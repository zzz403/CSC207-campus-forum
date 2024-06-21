package com.imperial.academia.app;

import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.interface_adapter.postboard.PostBoardController;
import com.imperial.academia.interface_adapter.postboard.PostBoardPresenter;
import com.imperial.academia.interface_adapter.postboard.PostBoardViewModel;
import com.imperial.academia.view.PostBoardView;

public class PostBoardUseCaseFactory {

    /** Prevent instantiation. */
    private PostBoardUseCaseFactory(){}

    public static PostBoardView create(ViewManagerModel viewManagerModel, PostBoardViewModel posterViewModel) throws ClassNotFoundException{
        PostBoardController posterController = new PostBoardController();
        PostBoardPresenter posterPresenter = new PostBoardPresenter(viewManagerModel, posterViewModel);
        return new PostBoardView(posterViewModel, posterController, posterPresenter);
    }
}
