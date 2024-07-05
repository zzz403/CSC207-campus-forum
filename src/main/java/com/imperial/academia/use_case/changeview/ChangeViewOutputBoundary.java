package com.imperial.academia.use_case.changeview;

/**
 * This interface represents the output boundary for the change view use case.
 * It defines the method required to change the current view.
 */
public interface ChangeViewOutputBoundary {
    
    /**
     * Changes the current view to the specified view name.
     * 
     * @param viewName the name of the view to change to
     */
    void changeView(String viewName);
}
