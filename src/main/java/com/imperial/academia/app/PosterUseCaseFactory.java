package com.imperial.academia.app;

import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.interface_adapter.poster.PosterController;
import com.imperial.academia.interface_adapter.poster.PosterPresenter;
import com.imperial.academia.interface_adapter.poster.PosterViewModel;
import com.imperial.academia.view.PosterView;

public class PosterUseCaseFactory {

    /** Prevent instantiation. */
    private PosterUseCaseFactory(){}

    public static PosterView create(ViewManagerModel viewManagerModel, PosterViewModel posterViewModel) throws ClassNotFoundException{
        PosterController posterController = new PosterController();
        PosterPresenter posterPresenter = new PosterPresenter(viewManagerModel, posterViewModel);
        return new PosterView(posterViewModel, posterController, posterPresenter);
    }
}
