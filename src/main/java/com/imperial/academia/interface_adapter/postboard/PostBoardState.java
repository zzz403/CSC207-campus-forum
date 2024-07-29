package com.imperial.academia.interface_adapter.postboard;

import java.util.ArrayList;
import java.util.List;

import com.imperial.academia.use_case.post.PostOverviewInfo;

/**
 * The PostBoardState class is the state for the post board view model.
 */
public class PostBoardState {
    // The list of post overview information.
    private List<PostOverviewInfo> postList;

    /**
     * Creates a new PostBoardState object.
     */
    public PostBoardState() {
        this.postList = new ArrayList<>();
    }

    /**
     * Get the list of post overview information.
     * 
     * @return the list of post overview information.
     */
    public List<PostOverviewInfo> getPostList() {
        return postList;
    }

    /**
     * Set the list of post overview information.
     * 
     * @param posts the list of post overview information to set.
     */
    public void setPostList(List<PostOverviewInfo> posts) {
        this.postList = new ArrayList<>(posts);
    }

}
