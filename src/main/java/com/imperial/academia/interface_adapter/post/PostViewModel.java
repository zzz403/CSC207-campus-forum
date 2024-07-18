package com.imperial.academia.interface_adapter.post;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.imperial.academia.interface_adapter.common.ViewModel;

/**
 * The PostViewModel class represents the ViewModel for a post,
 * managing the state and notifying listeners of property changes.
 */
public class PostViewModel extends ViewModel {

    private PostState state = new PostState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a new PostViewModel.
     */
    public PostViewModel() {
        super("post");
    }

    /**
     * Notifies listeners about the change in the state.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, state);
    }

    /**
     * Adds a PropertyChangeListener to the listener list.
     * 
     * @param listener the PropertyChangeListener to be added.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Sets the title of the post state and notifies listeners.
     * 
     * @param title the new title of the post.
     */
    public void setStateTitle(String title) {
        String oldTitle = state.getTitle();
        state.setTitle(title);
        support.firePropertyChange("postTitle", oldTitle, title);
    }

    /**
     * Gets the title of the post state.
     * 
     * @return the title of the post.
     */
    public String getStateTitle() {
        return state.getTitle();
    }

    /**
     * Sets the content of the post state and notifies listeners.
     * 
     * @param content the new content of the post.
     */
    public void setStateContent(String content) {
        String oldContent = state.getContent();
        state.setContent(content);
        support.firePropertyChange("postContent", oldContent, content);
    }

    /**
     * Gets the content of the post state.
     * 
     * @return the content of the post.
     */
    public String getStateContent() {
        return state.getContent();
    }

    /**
     * Sets the username of the post state and notifies listeners.
     * 
     * @param username the new username of the post.
     */
    public void setStateUsername(String username) {
        String oldUsername = state.getUsername();
        state.setUsername(username);
        support.firePropertyChange("postUsername", oldUsername, username);
    }

    /**
     * Gets the username of the post state.
     * 
     * @return the username of the post.
     */
    public String getStateUsername() {
        return state.getUsername();
    }

    /**
     * Sets the avatar URL of the post state and notifies listeners.
     * 
     * @param avatarUrl the new avatar URL of the post.
     */
    public void setStateAvatarUrl(String avatarUrl) {
        String oldAvatarUrl = state.getAvatarUrl();
        state.setAvatarUrl(avatarUrl);
        support.firePropertyChange("postAvatarUrl", oldAvatarUrl, avatarUrl);
    }

    /**
     * Gets the avatar URL of the post state.
     * 
     * @return the avatar URL of the post.
     */
    public String getStateAvatarUrl() {
        return state.getAvatarUrl();
    }

    /**
     * Sets the date of the post state and notifies listeners.
     * 
     * @param date the new date of the post.
     */
    public void setStateDate(Timestamp date) {
        Timestamp oldDate = state.getDate();
        state.setDate(date);
        support.firePropertyChange("postDate", oldDate, date);
    }

    /**
     * Gets the date of the post state.
     * 
     * @return the date of the post.
     */
    public Timestamp getStateDate() {
        Timestamp date = state.getDate();
        return new Timestamp(date.getTime());
    }

    /**
     * Gets the formatted date of the post state as a String.
     * 
     * @return the formatted date of the post.
     */
    public String getFormattedStateDate() {
        Timestamp originalTimestamp = state.getDate();
        Date date = new Date(originalTimestamp.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}
