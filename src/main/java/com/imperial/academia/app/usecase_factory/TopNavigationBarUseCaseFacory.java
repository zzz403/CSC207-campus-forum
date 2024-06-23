package com.imperial.academia.app.usecase_factory;

import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarController;
import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarPresenter;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.changeview.ChangeViewInteractor;
import com.imperial.academia.use_case.changeview.ChangeViewOutputBoundary;
import com.imperial.academia.view.TopNavigationBar;

public class TopNavigationBarUseCaseFacory {
    
    private TopNavigationBarUseCaseFacory(){}

    public static TopNavigationBar create(ViewManagerModel viewManagerModel){
        TopNavigationBarController topNavigationBarController = createController(viewManagerModel);
        return new TopNavigationBar(topNavigationBarController);
    }

    private static TopNavigationBarController createController(ViewManagerModel viewManagerModel){
        ChangeViewOutputBoundary changeViewPresenter = new TopNavigationBarPresenter(viewManagerModel);
        ChangeViewInputBoundary changeViewInteractor = new ChangeViewInteractor(changeViewPresenter);
        return new TopNavigationBarController(changeViewInteractor);
    }
}
