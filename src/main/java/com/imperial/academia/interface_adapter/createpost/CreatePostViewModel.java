package com.imperial.academia.interface_adapter.createpost;

import com.imperial.academia.interface_adapter.common.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The CreatePostViewModel class represents the view model for the create post view.
 */
public class CreatePostViewModel extends ViewModel {
    public final String TITLE_LABEL = "Title";
    public final String BODY_LABEL = "Body";
    public final String SAVE_DRAFT_BUTTON_LABEL = "Save Draft";
    public final String POST_BUTTON_LABEL = "Post";

    private CreatePostState state = new CreatePostState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a new CreatePostViewModel.
     */
    public CreatePostViewModel() {
        super("create post");
    }

    // Getters and Setters

    /**
     * Gets the current state of the create post view.
     * 
     * @return The current state of the create post view.
     */
    public CreatePostState getState() {
        return new CreatePostState(state);
    }

    /**
     * Sets the current state of the create post view.
     * 
     * @param state The state to set.
     */
    public void setState(CreatePostState newState) {
        this.state = newState;
        support.firePropertyChange("state", null, state);
    }

    /**
     * Fires a property change event to notify listeners of state changes.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a PropertyChangeListener to the listener list.
     * 
     * @param listener The PropertyChangeListener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Sets the title in the state and fires a property change event.
     * 
     * @param title The title to set.
     */
    public void setStateTitle(String newTitle) {
        String oldTitle = state.getTitle();
        state.setTitle(newTitle);
        support.firePropertyChange("title", oldTitle, newTitle);
    }

    /**
     * @return the current title stored in state
     */
    public String getStateTitle(){
        return state.getTitle();
    }

    /**
     * Sets the newContent in the state and fires a property change event.
     * 
     * @param newContent The newContent to set.
     */
    public void setStateContent(String newContent) {
        String oldContent = state.getContent();
        state.setContent(newContent);
        support.firePropertyChange("content", oldContent, newContent);
    }

    /**
     * @return the current content stored in state
     */
    public String getStateContent(){
        return state.getContent();
    }


    /**
     * Set the current board name selected
     * 
     * @param newBoardName The newBoardName to set
     */
    public void setStateCurrentBoardName(String newBoardName){
        String oldBoardName = state.getCurrentBoardName();
        state.setCurrentBoardName(newBoardName);
        support.firePropertyChange("currentBoardName", oldBoardName, newBoardName);
    }


    /**
     * @return the current board name
     */
    public String getStateCurrentBoardName(){
        return state.getCurrentBoardName();
    }
}