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
    public void setStateTitle(String title) {
        state.setTitle(title);
        support.firePropertyChange("title", null, title);
    }

    /**
     * Sets the body in the state and fires a property change event.
     * 
     * @param body The body to set.
     */
    public void setBody(String body) {
        String oldBody = this.state.getBody();
        state.setBody(body);
        support.firePropertyChange("body", oldBody, body);
    }

    public String getBody(){
        return this.state.getBody();
    }
}