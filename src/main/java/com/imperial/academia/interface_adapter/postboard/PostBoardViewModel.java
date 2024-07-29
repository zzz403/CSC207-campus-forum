package com.imperial.academia.interface_adapter.postboard;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import com.imperial.academia.entity.post.Post;
import com.imperial.academia.interface_adapter.common.ViewModel;

public class PostBoardViewModel extends ViewModel {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private PostBoardState state = new PostBoardState();
    
    public PostBoardViewModel(){
        super("post board");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setStatePostList(List<Post> postList){
        List<Post> oldList = state.getPostList();
        state.setPostList(postList);
        support.firePropertyChange("post list", oldList, postList);
    }
}
