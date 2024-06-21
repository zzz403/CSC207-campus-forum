package com.imperial.academia.entity.board;

import java.sql.Timestamp;

public class Board {
    private int id;
    private String name;
    private String description;
    private int creatorId;
    private Timestamp creationDate;
    private Timestamp lastModified;

    public Board(int id, String name, String description, int creatorId, Timestamp creationDate, Timestamp lastModified) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
        this.creationDate = creationDate;
        this.lastModified = lastModified;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getCreatorId() { return creatorId; }
    public void setCreatorId(int creatorId) { this.creatorId = creatorId; }

    public Timestamp getCreationDate() { return creationDate; }
    public void setCreationDate(Timestamp creationDate) { this.creationDate = creationDate; }

    public Timestamp getLastModified() { return lastModified; }
    public void setLastModified(Timestamp lastModified) { this.lastModified = lastModified; }
}
