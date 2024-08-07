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
     * Add a post overview information to the list of post overview information.
     * 
     * @param postOverviewInfo the post overview information to add.
     */
    public void addPost(PostOverviewInfo postOverviewInfo) {
        postList.add(postOverviewInfo);
    }

    /**
     * Set the list of post overview information.
     * 
     * @param posts the list of post overview information to set.
     */
    public void setPostList(List<PostOverviewInfo> posts) {
        this.postList = new ArrayList<>(posts);
    }

    /**
     * Increment the likes of the post with the given post ID.
     * 
     * @param postId the ID of the post to increment the likes of.
     */
    public void incrementLikesByPostId(int postId) {
        for (PostOverviewInfo post : postList) {
            if (post.getPostID() == postId) {
                int likes = post.getLikes();
                post.setLikes(likes + 1);
                return;
            }
        }
    }

    /**
     * Decrement the likes of the post with the given post ID.
     * 
     * @param postId the ID of the post to decrement the likes of.
     */
    public void decrementLikesByPostId(int postId) {
        for (PostOverviewInfo post : postList) {
            if (post.getPostID() == postId) {
                int likes = post.getLikes();
                post.setLikes(likes - 1);
                return;
            }
        }
    }

    /**
     * Get the likes of the post with the given post ID.
     * 
     * @param postId the ID of the post to get the likes of.
     * @return the likes of the post with the given post ID.
     */
    public int getLikesByPostId(int postId) {
        for (PostOverviewInfo post : postList) {
            if (post.getPostID() == postId) {
                return post.getLikes();
            }
        }
        return 0;
    }

    /**
     * Update the isLiked field of the post with the given post ID.
     * 
     * @param postId   the ID of the post to update.
     * @param isLiked the new value of the isLiked field.
     */
    public void updateIsLikeByPostId(int postId, boolean isLiked) {
        for (PostOverviewInfo post : postList) {
            if (post.getPostID() == postId) {
                post.setLiked(isLiked);
                return;
            }
        }
    }

    public boolean getIsLikeByPostId(int postID) {
        for (PostOverviewInfo post : postList) {
            if (post.getPostID() == postID) {
                return post.isLiked();
            }
        }
        return false;
    }
}
