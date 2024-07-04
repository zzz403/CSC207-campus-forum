package com.imperial.academia.use_case.changeview;

/**
 * This interface represents the input boundary for the change view use case.
 * It defines the method required to request a view change.
 */
public interface ChangeViewInputBoundary {
    
    /**
     * Requests a change to the specified view name.
     * 
     * @param viewName the name of the view to change to
     */
    void changeView(String viewName);
}
