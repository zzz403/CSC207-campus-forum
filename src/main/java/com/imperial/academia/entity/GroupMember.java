package com.imperial.academia.entity;

import java.sql.Timestamp;

public class GroupMember {
    private int groupId;
    private int userId;
    private String role;
    private Timestamp joinedDate;

    public GroupMember(int groupId, int userId, String role, Timestamp joinedDate) {
        this.groupId = groupId;
        this.userId = userId;
        this.role = role;
        this.joinedDate = joinedDate;
    }

    // Getters and Setters
    public int getGroupId() { return groupId; }
    public void setGroupId(int groupId) { this.groupId = groupId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Timestamp getJoinedDate() { return joinedDate; }
    public void setJoinedDate(Timestamp joinedDate) { this.joinedDate = joinedDate; }
}
