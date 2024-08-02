package com.imperial.academia.interface_adapter.post;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imperial.academia.interface_adapter.common.ViewModel;
import com.imperial.academia.use_case.post.CommentData;

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

    /**
     * Sets the number of likes of the post state and notifies listeners.
     * 
     * @param likes the new number of likes of the post.
     */
    public void setStateLikes(int likes) {
        int oldLikes = state.getLikes();
        state.setLikes(likes);
        support.firePropertyChange("postLikes", oldLikes, likes);
    }

    /**
     * Gets the number of likes of the post state.
     * 
     * @return the number of likes of the post.
     */
    public int getStateLikes() {
        return state.getLikes();
    }

    /**
     * Sets the post ID of the post state and notifies listeners.
     * 
     * @param postId the new post ID of the post.
     */
    public void setStatePostID(int postId) {
        int oldPostId = state.getPostID();
        state.setPostID(postId);
        support.firePropertyChange("postId", oldPostId, postId);
    }

    /**
     * Gets the post ID of the post state.
     * 
     * @return the post ID of the post.
     */
    public int getStatePostId() {
        return state.getPostID();
    }

    /**
     * Increments the number of likes of the post state and notifies listeners.
     */
    public void incrementStateLikes() {
        int oldLikes = state.getLikes();
        state.setLikes(oldLikes + 1);
        support.firePropertyChange("postLikes", oldLikes, oldLikes + 1);
    }

    /**
     * Decrements the number of likes of the post state and notifies listeners.
     */
    public void decrementStateLikes() {
        int oldLikes = state.getLikes();
        if(oldLikes-1 < 0) {
            return;
        }
        state.setLikes(oldLikes - 1);
        support.firePropertyChange("postLikes", oldLikes, oldLikes - 1);
    }

    /**
     * Sets the liked state of the post and notifies listeners.
     * 
     * @param isLiked the new liked state of the post.
     */
    public void setStateIsLiked(boolean isLiked) {
        state.setLiked(isLiked);
        support.firePropertyChange("isLiked", null, isLiked);
    }

    /**
     * Gets the liked state of the post.
     * 
     * @return true if the post is liked, false otherwise.
     */
    public boolean getStateIsLiked() {
        return state.isLiked();
    }

    /**
     * Sets the comments of the post state and notifies listeners.
     * 
     * @param comments the new comments of the post.
     */
    public void setStateComments(List<CommentData> comments) {
        List<CommentData> oldComments = state.getComments();
        state.setComments(comments);
        support.firePropertyChange("comments", oldComments, comments);
    }

    /**
     * Gets the comments of the post state.
     * 
     * @return the comments of the post.
     */
    public List<CommentData> getStateComments() {
        return new ArrayList<>(this.state.getComments());
    }

    /**
     * Adds a comment to the post state and notifies listeners.
     * 
     * @param commentData the comment to add.
     */
    public void addStateComment(CommentData commentData) {
        this.state.addComment(commentData);
        support.firePropertyChange("addComment", null, this.state);
    }
}
