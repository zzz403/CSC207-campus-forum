package com.imperial.academia.entity.chat_group;

import java.sql.Timestamp;

public class ChatGroupDTO {
    private int id;
    private String groupName;
    private Timestamp lastModified;
    private boolean isGroup;

    public ChatGroupDTO(int id, String groupName, boolean isGroup, Timestamp lastModified) {
        this.id = id;
        this.groupName = groupName;
        this.isGroup = isGroup;
        this.lastModified = lastModified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }
}
