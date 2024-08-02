package com.imperial.academia.interface_adapter.createpost;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the state of the Create Post view model.
 * This class holds the data required for creating a post, 
 * including the title, content, list of board names, and current board name.
 */
public class CreatePostState {
    
    /** The title of the post. */
    private String title;
    
    /** The content of the post. */
    private String content;
    
    /** The current select board name */
    private String currentBoardName;

    /** The list of board names. */
    private List<String> boardsName;

    private boolean isSave;


    /**
     * Constructs a new CreatePostState by copying the state from another instance.
     * 
     * @param other the instance to copy the state from
     */
    public CreatePostState(CreatePostState other) {
        this.title = other.getTitle();
        this.content = other.getContent();
        this.boardsName = other.getBoardsName();
        this.currentBoardName = other.getCurrentBoardName();
    }

    /**
     * Constructs a new CreatePostState with default values.
     */
    public CreatePostState() {
        this.title = "";
        this.content = "";
        this.currentBoardName = "";
        this.boardsName = new ArrayList<>();
        this.isSave = false;
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
     * Gets the content of the post.
     * 
     * @return the content of the post
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the post.
     * 
     * @param body the content of the post
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the list of board names.
     * 
     * @return the list of board names
     */
    public List<String> getBoardsName() {
        return boardsName;
    }

    /**
     * Sets the list of board names.
     * 
     * @param boardName the list of board names
     */
    public void setBoardsName(List<String> boardName) {
        this.boardsName = new ArrayList<>(boardName);
    }

    
    /**
     * Get the current board name
     * 
     * @return the current selected board name
     */
    public String getCurrentBoardName() {
        return currentBoardName;
    }

    /**
     * Set the current selected board name
     * 
     * @param currentBoardName the current selected board name
     */
    public void setCurrentBoardName(String currentBoardName) {
        this.currentBoardName = currentBoardName;
    }

    public void setIsSave(boolean b) {
        this.isSave = b;
    }

    public boolean getIsSave() {
        return this.isSave;
    }

    public int getCurrentBoardIndex() {
        return boardsName.indexOf(currentBoardName);
    }
}
