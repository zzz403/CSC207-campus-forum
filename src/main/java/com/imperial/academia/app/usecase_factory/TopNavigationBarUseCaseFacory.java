package com.imperial.academia.app.usecase_factory;

import java.io.IOException;

import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarController;
import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarPresenter;
import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarViewModel;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.changeview.ChangeViewInteractor;
import com.imperial.academia.use_case.changeview.ChangeViewOutputBoundary;
import com.imperial.academia.use_case.session.SessionInputBoundary;
import com.imperial.academia.use_case.session.SessionInteractor;
import com.imperial.academia.view.components.TopNavigationBar;

public class TopNavigationBarUseCaseFacory {
    
    private TopNavigationBarUseCaseFacory(){}

    public static TopNavigationBar create(ViewManagerModel viewManagerModel,TopNavigationBarViewModel topNavigationBarViewModel) throws IOException{
        TopNavigationBarController topNavigationBarController = createController(viewManagerModel,topNavigationBarViewModel);
        return new TopNavigationBar(topNavigationBarController,topNavigationBarViewModel);
    }

    private static TopNavigationBarController createController(ViewManagerModel viewManagerModel ,TopNavigationBarViewModel topNavigationBarViewModel){
        ChangeViewOutputBoundary changeViewPresenter = new TopNavigationBarPresenter(viewManagerModel,topNavigationBarViewModel);
        ChangeViewInputBoundary changeViewInteractor = new ChangeViewInteractor(changeViewPresenter);
        SessionInputBoundary sessionInputBoundary = new SessionInteractor();
        return new TopNavigationBarController(changeViewInteractor,sessionInputBoundary);
    }
}
