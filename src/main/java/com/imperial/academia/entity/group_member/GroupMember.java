package com.imperial.academia.entity.group_member;

import java.sql.Timestamp;

public class GroupMember {
    private int groupId;
    private int userId;
    private String role;
    private Timestamp joinedDate;
    private Timestamp lastModified;

    public GroupMember(int groupId, int userId, String role, Timestamp joinedDate, Timestamp lastModified) {
        this.groupId = groupId;
        this.userId = userId;
        this.role = role;
        this.joinedDate = joinedDate;
        this.lastModified = lastModified;
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

    public Timestamp getLastModified() { return lastModified; }
    public void setLastModified(Timestamp lastModified) { this.lastModified = lastModified; }
}
