package com.imperial.academia.interface_adapter.topnavbar;

import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.use_case.changeview.ChangeViewOutputBoundary;

/**
 * The presenter for the top navigation bar.
 * It handles the logic for changing the view and updates the view model accordingly.
 */
public class TopNavigationBarPresenter implements ChangeViewOutputBoundary {
    
    /** The model that manages the views. */
    private final ViewManagerModel viewManagerModel;
    
    /** The view model associated with the top navigation bar. */
    private final TopNavigationBarViewModel topNavigationBarViewModel;

    /**
     * Constructs a new TopNavigationBarPresenter with the specified view manager model and top navigation bar view model.
     * 
     * @param viewManagerModel the model that manages the views
     * @param topNavigationBarViewModel the view model associated with the top navigation bar
     */
    public TopNavigationBarPresenter(ViewManagerModel viewManagerModel, TopNavigationBarViewModel topNavigationBarViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.topNavigationBarViewModel = topNavigationBarViewModel;
    }

    /**
     * Changes the view to the specified view name.
     * Updates the view model and notifies the view manager and the navigation bar view model of the change.
     * 
     * @param viewName the name of the view to change to
     */
    @Override
    public void changeView(String viewName) {
        TopNavigationBarState state = topNavigationBarViewModel.getState();
        state.setCurrentViewName(viewName);
        topNavigationBarViewModel.setState(state);

        viewManagerModel.setActiveView(viewName);

        topNavigationBarViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }
}
