package com.imperial.academia.entity.group_member;

import java.sql.Timestamp;

/**
 * The GroupMember class represents a member of a group entity with relevant details.
 */
public class GroupMember {
    private int groupId;
    private int userId;
    private String role;
//    private boolean isPrevent;
    private Timestamp joinedDate;
    private Timestamp lastModified;

    /**
     * Constructs a new GroupMember with the specified details.
     * 
     * @param groupId The ID of the group.
     * @param userId The ID of the user.
     * @param role The role of the user in the group.
     * @param joinedDate The timestamp when the user joined the group.
     * @param lastModified The timestamp when the group membership was last modified.
     */
    public GroupMember(int groupId, int userId, String role, Timestamp joinedDate, Timestamp lastModified) {
        this.groupId = groupId;
        this.userId = userId;
        this.role = role;
        this.joinedDate = joinedDate;
        this.lastModified = lastModified;
    }

    // Getters and Setters

    /**
     * Gets the ID of the group.
     * 
     * @return The ID of the group.
     */
    public int getGroupId() { 
        return groupId; 
    }

    /**
     * Sets the ID of the group.
     * 
     * @param groupId The ID of the group.
     */
    public void setGroupId(int groupId) { 
        this.groupId = groupId; 
    }

    /**
     * Gets the ID of the user.
     * 
     * @return The ID of the user.
     */
    public int getUserId() { 
        return userId; 
    }

    /**
     * Sets the ID of the user.
     * 
     * @param userId The ID of the user.
     */
    public void setUserId(int userId) { 
        this.userId = userId; 
    }

    /**
     * Gets the role of the user in the group.
     * 
     * @return The role of the user in the group.
     */
    public String getRole() { 
        return role; 
    }

    /**
     * Sets the role of the user in the group.
     * 
     * @param role The role of the user in the group.
     */
    public void setRole(String role) { 
        this.role = role; 
    }

    /**
     * Gets the timestamp when the user joined the group.
     * 
     * @return The timestamp when the user joined the group.
     */
    public Timestamp getJoinedDate() { 
        return joinedDate; 
    }

    /**
     * Sets the timestamp when the user joined the group.
     * 
     * @param joinedDate The timestamp when the user joined the group.
     */
    public void setJoinedDate(Timestamp joinedDate) { 
        this.joinedDate = joinedDate; 
    }

    /**
     * Gets the timestamp when the group membership was last modified.
     * 
     * @return The timestamp when the group membership was last modified.
     */
    public Timestamp getLastModified() { 
        return lastModified; 
    }

    /**
     * Sets the timestamp when the group membership was last modified.
     * 
     * @param lastModified The timestamp when the group membership was last modified.
     */
    public void setLastModified(Timestamp lastModified) { 
        this.lastModified = lastModified; 
    }

}
