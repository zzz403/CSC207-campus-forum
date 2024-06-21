package com.imperial.academia.entity.board;

import com.google.protobuf.Timestamp;

public class Board {
    private int id;
    private String name;
    private String description;
    private int creatorId;
    private Timestamp creationDate;

    public Board(int id, String name, String description, int creatorId, java.sql.Timestamp timestamp) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
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
}
