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
}
