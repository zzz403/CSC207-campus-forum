package com.imperial.academia.entity.post;

import java.sql.Timestamp;

/**
 * The Post class represents a post entity with relevant details.
 */
public class Post {
    private int id;
    private String title;
    private String content;
    private int authorId;
    private int boardId;
    private Timestamp creationDate;
    private Timestamp lastModifiedDate;

    /**
     * Constructs a new Post with the specified details.
     * 
     * @param id The unique identifier of the post.
     * @param title The title of the post.
     * @param content The content of the post.
     * @param authorId The ID of the user who authored the post.
     * @param boardId The ID of the board the post is associated with.
     * @param creationDate The timestamp when the post was created.
     * @param lastModifiedDate The timestamp when the post was last modified.
     */
    public Post(int id, String title, String content, int authorId, int boardId, Timestamp creationDate, Timestamp lastModifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.boardId = boardId;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier of the post.
     * 
     * @return The unique identifier of the post.
     */
    public int getId() { 
        return id; 
    }

    /**
     * Sets the unique identifier of the post.
     * 
     * @param id The unique identifier of the post.
     */
    public void setId(int id) { 
        this.id = id; 
    }

    /**
     * Gets the title of the post.
     * 
     * @return The title of the post.
     */
    public String getTitle() { 
        return title; 
    }

    /**
     * Sets the title of the post.
     * 
     * @param title The title of the post.
     */
    public void setTitle(String title) { 
        this.title = title; 
    }

    /**
     * Gets the content of the post.
     * 
     * @return The content of the post.
     */
    public String getContent() { 
        return content; 
    }

    /**
     * Sets the content of the post.
     * 
     * @param content The content of the post.
     */
    public void setContent(String content) { 
        this.content = content; 
    }

    /**
     * Gets the ID of the user who authored the post.
     * 
     * @return The ID of the user who authored the post.
     */
    public int getAuthorId() { 
        return authorId; 
    }

    /**
     * Sets the ID of the user who authored the post.
     * 
     * @param authorId The ID of the user who authored the post.
     */
    public void setAuthorId(int authorId) { 
        this.authorId = authorId; 
    }

    /**
     * Gets the ID of the board the post is associated with.
     * 
     * @return The ID of the board the post is associated with.
     */
    public int getBoardId() { 
        return boardId; 
    }

    /**
     * Sets the ID of the board the post is associated with.
     * 
     * @param boardId The ID of the board the post is associated with.
     */
    public void setBoardId(int boardId) { 
        this.boardId = boardId; 
    }

    /**
     * Gets the timestamp when the post was created.
     * 
     * @return The timestamp when the post was created.
     */
    public Timestamp getCreationDate() { 
        return creationDate; 
    }

    /**
     * Sets the timestamp when the post was created.
     * 
     * @param creationDate The timestamp when the post was created.
     */
    public void setCreationDate(Timestamp creationDate) { 
        this.creationDate = creationDate; 
    }

    /**
     * Gets the timestamp when the post was last modified.
     * 
     * @return The timestamp when the post was last modified.
     */
    public Timestamp getLastModifiedDate() { 
        return lastModifiedDate; 
    }

    /**
     * Sets the timestamp when the post was last modified.
     * 
     * @param lastModifiedDate The timestamp when the post was last modified.
     */
    public void setLastModifiedDate(Timestamp lastModifiedDate) { 
        this.lastModifiedDate = lastModifiedDate; 
    }
}
