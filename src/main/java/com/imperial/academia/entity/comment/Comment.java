package com.imperial.academia.entity.comment;

import java.sql.Timestamp;

public class Comment {
    private int id;
    private String content;
    private int authorId;
    private int postId;
    private Integer parentCommentId;
    private Timestamp creationDate;

    public Comment(int id, String content, int authorId, int postId, Integer parentCommentId, Timestamp creationDate) {
        this.id = id;
        this.content = content;
        this.authorId = authorId;
        this.postId = postId;
        this.parentCommentId = parentCommentId;
        this.creationDate = creationDate;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getAuthorId() { return authorId; }
    public void setAuthorId(int authorId) { this.authorId = authorId; }

    public int getPostId() { return postId; }
    public void setPostId(int postId) { this.postId = postId; }

    public Integer getParentCommentId() { return parentCommentId; }
    public void setParentCommentId(Integer parentCommentId) { this.parentCommentId = parentCommentId; }

    public Timestamp getCreationDate() { return creationDate; }
    public void setCreationDate(Timestamp creationDate) { this.creationDate = creationDate; }
}

