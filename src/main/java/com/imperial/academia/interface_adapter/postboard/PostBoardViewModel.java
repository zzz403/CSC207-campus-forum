package com.imperial.academia.interface_adapter.postboard;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import com.imperial.academia.interface_adapter.common.ViewModel;
import com.imperial.academia.use_case.post.PostOverviewInfo;

/**
 * The PostBoardViewModel class is the view model for the post board view.
 */
public class PostBoardViewModel extends ViewModel {

    /**
     * The PropertyChangeSupport object for the view model.
     */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * The PostBoardState object for the view model.
     */
    private PostBoardState state = new PostBoardState();

    /**
     * Creates a new PostBoardViewModel object.
     */
    public PostBoardViewModel() {
        super("post board");
    }

    /**
     * Fires a property change event for the view model.
     * 
     * This method can be called when the state of the view model changes.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to the view model.
     * 
     * @param listener the property change listener to add.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Get the post info list in state
     * 
     * @return the list of post overview information in the state
     */
    public List<PostOverviewInfo> getPostInfoList() {
        List<PostOverviewInfo> postInfoList = new ArrayList<>(this.state.getPostList());
        return postInfoList;
    }

    /**
     * Sets the post list of the view model.
     * 
     * @param postList the list of post overview information to set the post list
     *                 to.
     */
    public void setStatePostList(List<PostOverviewInfo> postList) {
        List<PostOverviewInfo> oldList = state.getPostList();
        state.setPostList(postList);
        support.firePropertyChange("post list", oldList, postList);
    }

    /**
     * Adds a post information to the state.
     * 
     * @param postOverviewInfo the post overview information to add to the state.
     */
    public void addOnePostInfoToState(PostOverviewInfo postOverviewInfo) {
        state.addPost(postOverviewInfo);
        support.firePropertyChange("addPost", null, state);
    }

    /**
     * Increments the likes of a post by the post ID.
     * 
     * @param postId the post ID to increment the likes of.
     */
    public void incrementStateLikesByPostId(int postId) {
        System.out.println("incrementStateLikesByPostId");
        state.incrementLikesByPostId(postId);
        updateStateIsLikeByPostId(postId, true);
        support.firePropertyChange("likeChange="+postId, null, state);
    }

    /**
     * Decrements the likes of a post by the post ID.
     * 
     * @param postId the post ID to decrement the likes of.
     */
    public void decrementStateLikesByPostId(int postId) {
        System.out.println("decrementStateLikesByPostId");
        state.decrementLikesByPostId(postId);
        updateStateIsLikeByPostId(postId, false);
        support.firePropertyChange("likeChange="+postId, null, state);
    }

    /**
     * Gets the likes of a post by the post ID.
     * 
     * @param postId the post ID to get the likes of.
     * @return the number of likes of the post.
     */
    public int getPostLikesByPostId(int postId) {
        return state.getLikesByPostId(postId);
    }

    /**
     * Updates the isLiked field of a post with the given ID.
     * 
     * @param postId  the ID of the post to update.
     * @param isLiked the new value of the isLiked field.
     */
    public void updateStateIsLikeByPostId(int postId, boolean isLiked) {
        state.updateIsLikeByPostId(postId, isLiked);
        support.firePropertyChange("isLiked="+postId, null, state);
    }

    /**
     * Gets the isLiked field of a post by the post ID.
     * 
     * @param postID the post ID to get the isLiked field of.
     * @return the isLiked field of the post.
     */
    public boolean getIsLikeByPostId(int postID) {
        return state.getIsLikeByPostId(postID);
    }
}
