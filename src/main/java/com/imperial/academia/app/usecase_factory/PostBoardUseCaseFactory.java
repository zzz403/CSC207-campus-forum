package com.imperial.academia.app.usecase_factory;

import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.interface_adapter.postboard.PostBoardController;
import com.imperial.academia.interface_adapter.postboard.PostBoardPresenter;
import com.imperial.academia.interface_adapter.postboard.PostBoardViewModel;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.changeview.ChangeViewInteractor;
import com.imperial.academia.use_case.changeview.ChangeViewOutputBoundary;
import com.imperial.academia.view.PostBoardView;

public class PostBoardUseCaseFactory {

    /** Prevent instantiation. */
    private PostBoardUseCaseFactory(){}

    public static PostBoardView create(ViewManagerModel viewManagerModel, PostBoardViewModel posterViewModel) throws ClassNotFoundException{
        PostBoardController posterController = createController(viewManagerModel);
        return new PostBoardView(posterViewModel, posterController);
    }

    private static PostBoardController createController(ViewManagerModel viewManagerModel){
        ChangeViewOutputBoundary postBoardPresenter = new PostBoardPresenter(viewManagerModel);
        ChangeViewInputBoundary changeViewInteractor = new ChangeViewInteractor(postBoardPresenter);
        return new PostBoardController(changeViewInteractor);
    }
}
