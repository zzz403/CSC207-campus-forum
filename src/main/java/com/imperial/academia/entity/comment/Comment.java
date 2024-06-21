package com.imperial.academia.entity.comment;

import java.sql.Timestamp;

/**
 * The Comment class represents a comment entity with relevant details.
 */
public class Comment {
    private int id;
    private String content;
    private int authorId;
    private int postId;
    private Integer parentCommentId;
    private Timestamp creationDate;
    private Timestamp lastModified;

    /**
     * Constructs a new Comment with the specified details.
     * 
     * @param id The unique identifier of the comment.
     * @param content The content of the comment.
     * @param authorId The ID of the user who authored the comment.
     * @param postId The ID of the post the comment is associated with.
     * @param parentCommentId The ID of the parent comment, if any.
     * @param creationDate The timestamp when the comment was created.
     * @param lastModified The timestamp when the comment was last modified.
     */
    public Comment(int id, String content, int authorId, int postId, Integer parentCommentId, Timestamp creationDate, Timestamp lastModified) {
        this.id = id;
        this.content = content;
        this.authorId = authorId;
        this.postId = postId;
        this.parentCommentId = parentCommentId;
        this.creationDate = creationDate;
        this.lastModified = lastModified;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier of the comment.
     * 
     * @return The unique identifier of the comment.
     */
    public int getId() { 
        return id; 
    }

    /**
     * Sets the unique identifier of the comment.
     * 
     * @param id The unique identifier of the comment.
     */
    public void setId(int id) { 
        this.id = id; 
    }

    /**
     * Gets the content of the comment.
     * 
     * @return The content of the comment.
     */
    public String getContent() { 
        return content; 
    }

    /**
     * Sets the content of the comment.
     * 
     * @param content The content of the comment.
     */
    public void setContent(String content) { 
        this.content = content; 
    }

    /**
     * Gets the ID of the user who authored the comment.
     * 
     * @return The ID of the user who authored the comment.
     */
    public int getAuthorId() { 
        return authorId; 
    }

    /**
     * Sets the ID of the user who authored the comment.
     * 
     * @param authorId The ID of the user who authored the comment.
     */
    public void setAuthorId(int authorId) { 
        this.authorId = authorId; 
    }

    /**
     * Gets the ID of the post the comment is associated with.
     * 
     * @return The ID of the post the comment is associated with.
     */
    public int getPostId() { 
        return postId; 
    }

    /**
     * Sets the ID of the post the comment is associated with.
     * 
     * @param postId The ID of the post the comment is associated with.
     */
    public void setPostId(int postId) { 
        this.postId = postId; 
    }

    /**
     * Gets the ID of the parent comment, if any.
     * 
     * @return The ID of the parent comment, if any.
     */
    public Integer getParentCommentId() { 
        return parentCommentId; 
    }

    /**
     * Sets the ID of the parent comment, if any.
     * 
     * @param parentCommentId The ID of the parent comment, if any.
     */
    public void setParentCommentId(Integer parentCommentId) { 
        this.parentCommentId = parentCommentId; 
    }

    /**
     * Gets the timestamp when the comment was created.
     * 
     * @return The timestamp when the comment was created.
     */
    public Timestamp getCreationDate() { 
        return creationDate; 
    }

    /**
     * Sets the timestamp when the comment was created.
     * 
     * @param creationDate The timestamp when the comment was created.
     */
    public void setCreationDate(Timestamp creationDate) { 
        this.creationDate = creationDate; 
    }

    /**
     * Gets the timestamp when the comment was last modified.
     * 
     * @return The timestamp when the comment was last modified.
     */
    public Timestamp getLastModified() { 
        return lastModified; 
    }

    /**
     * Sets the timestamp when the comment was last modified.
     * 
     * @param lastModified The timestamp when the comment was last modified.
     */
    public void setLastModified(Timestamp lastModified) { 
        this.lastModified = lastModified; 
    }
}
