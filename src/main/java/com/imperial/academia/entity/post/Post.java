package com.imperial.academia.entity.post;

import java.sql.Timestamp;

public class Post {
    private int id;
    private String title;
    private String content;
    private int authorId;
    private int boardId;
    private Timestamp creationDate;
    private Timestamp lastModifiedDate;

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
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getAuthorId() { return authorId; }
    public void setAuthorId(int authorId) { this.authorId = authorId; }

    public int getBoardId() { return boardId; }
    public void setBoardId(int boardId) { this.boardId = boardId; }

    public Timestamp getCreationDate() { return creationDate; }
    public void setCreationDate(Timestamp creationDate) { this.creationDate = creationDate; }

    public Timestamp getLastModifiedDate() { return lastModifiedDate; }
    public void setLastModifiedDate(Timestamp lastModifiedDate) { this.lastModifiedDate = lastModifiedDate; }
}

