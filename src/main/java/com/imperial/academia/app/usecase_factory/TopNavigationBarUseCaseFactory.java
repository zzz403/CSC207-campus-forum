package com.imperial.academia.app.usecase_factory;

import java.io.IOException;

import javax.swing.JFrame;

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

/**
 * Factory class for creating instances related to the Top Navigation Bar use case.
 */
public class TopNavigationBarUseCaseFactory {

    /** Singleton instance of the top navigation bar controller. */
    private static TopNavigationBarController topNavigationBarController = null;
    
    /** Prevents instantiation of this utility class. */
    private TopNavigationBarUseCaseFactory() {}

    /**
     * Creates a new instance of {@link TopNavigationBar} with the specified view manager model,
     * top navigation bar view model, and frame.
     * 
     * @param viewManagerModel the view manager model
     * @param topNavigationBarViewModel the top navigation bar view model
     * @param frame the frame to which the top navigation bar will be added
     * @return a new instance of {@link TopNavigationBar}
     * @throws IOException if an I/O error occurs
     */
    public static TopNavigationBar create(ViewManagerModel viewManagerModel, TopNavigationBarViewModel topNavigationBarViewModel, JFrame frame) throws IOException {
        if (topNavigationBarController == null) {
            topNavigationBarController = createController(viewManagerModel, topNavigationBarViewModel);
        }
        return new TopNavigationBar(topNavigationBarController, topNavigationBarViewModel, frame);
    }

    /**
     * Creates a new instance of {@link TopNavigationBarController} with the specified view manager model
     * and top navigation bar view model.
     * 
     * @param viewManagerModel the view manager model
     * @param topNavigationBarViewModel the top navigation bar view model
     * @return a new instance of {@link TopNavigationBarController}
     */
    private static TopNavigationBarController createController(ViewManagerModel viewManagerModel, TopNavigationBarViewModel topNavigationBarViewModel) {
        ChangeViewOutputBoundary changeViewPresenter = new TopNavigationBarPresenter(viewManagerModel, topNavigationBarViewModel);
        ChangeViewInputBoundary changeViewInteractor = new ChangeViewInteractor(changeViewPresenter);
        SessionInputBoundary sessionInputBoundary = new SessionInteractor();
        return new TopNavigationBarController(changeViewInteractor, sessionInputBoundary);
    }
}
