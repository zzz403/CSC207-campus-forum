package com.imperial.academia.entity.chat_group;

import java.sql.Timestamp;

/**
 * The ChatGroup class represents a chat group entity with relevant details.
 */
public class ChatGroup {
    private int id;
    private String groupName;
    private Timestamp creationDate;
    private boolean isGroup;
    private Timestamp lastModified;

    /**
     * Constructs a new ChatGroup with the specified details.
     * 
     * @param id           The unique identifier of the chat group.
     * @param groupName    The name of the chat group.
     * @param creationDate The timestamp when the chat group was created.
     * @param lastModified The timestamp when the chat group was last modified.
     */
    public ChatGroup(int id, String groupName, boolean isGroup, Timestamp creationDate, Timestamp lastModified) {
        this.id = id;
        this.groupName = groupName;
        this.isGroup = isGroup;
        this.creationDate = creationDate;
        this.lastModified = lastModified;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier of the chat group.
     * 
     * @return The unique identifier of the chat group.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the chat group.
     * 
     * @param id The unique identifier of the chat group.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the chat group.
     * 
     * @return The name of the chat group.
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Sets the name of the chat group.
     * 
     * @param groupName The name of the chat group.
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * Gets the timestamp when the chat group was created.
     * 
     * @return The timestamp when the chat group was created.
     */
    public Timestamp getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the timestamp when the chat group was created.
     * 
     * @param creationDate The timestamp when the chat group was created.
     */
    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets the timestamp when the chat group was last modified.
     * 
     * @return The timestamp when the chat group was last modified.
     */
    public Timestamp getLastModified() {
        return lastModified;
    }

    /**
     * Sets the timestamp when the chat group was last modified.
     * 
     * @param lastModified The timestamp when the chat group was last modified.
     */
    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * Gets whether the chat group is a group or not.
     * 
     * @return True if the chat group is a group, false otherwise.
     */
    public boolean isGroup() {
        return isGroup;
    }

    /**
     * Sets whether the chat group is a group or not.
     * 
     * @param isGroup True if the chat group is a group, false otherwise.
     */
    public void setGroup(boolean isGroup) {
        this.isGroup = isGroup;
    }
}
