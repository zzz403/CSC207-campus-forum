package com.imperial.academia.interface_adapter.createpost;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the state of the Create Post view model.
 * This class holds the data required for creating a post, including the title, body, and list of board names.
 */
public class CreatePostState {
    
    /** The title of the post. */
    private String title;
    
    /** The body of the post. */
    private String body;
    
    /** The list of board names. */
    private List<String> boardName;

    /**
     * Constructs a new CreatePostState by copying the state from another instance.
     * 
     * @param other the instance to copy the state from
     */
    public CreatePostState(CreatePostState other) {
        this.title = other.getTitle();
        this.body = other.getBody();
        this.boardName = other.getBoardName();
    }

    /**
     * Constructs a new CreatePostState with default values.
     */
    public CreatePostState() {
        this.title = "";
        this.body = "";
        this.boardName = new ArrayList<>();
    }

    /**
     * Gets the title of the post.
     * 
     * @return the title of the post
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the post.
     * 
     * @param title the title of the post
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the body of the post.
     * 
     * @return the body of the post
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the body of the post.
     * 
     * @param body the body of the post
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Gets the list of board names.
     * 
     * @return the list of board names
     */
    public List<String> getBoardName() {
        return boardName;
    }

    /**
     * Sets the list of board names.
     * 
     * @param boardName the list of board names
     */
    public void setBoardName(List<String> boardName) {
        this.boardName = new ArrayList<>(boardName);
    }
}
