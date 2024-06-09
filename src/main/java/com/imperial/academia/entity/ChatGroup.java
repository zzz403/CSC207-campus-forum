package com.imperial.academia.entity;

import java.sql.Timestamp;

public class ChatGroup {
    private int id;
    private String groupName;
    private Timestamp creationDate;

    public ChatGroup(int id, String groupName, Timestamp creationDate) {
        this.id = id;
        this.groupName = groupName;
        this.creationDate = creationDate;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }

    public Timestamp getCreationDate() { return creationDate; }
    public void setCreationDate(Timestamp creationDate) { this.creationDate = creationDate; }
}
