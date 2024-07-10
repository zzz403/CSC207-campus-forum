package com.imperial.academia.entity.chat_group;

import java.sql.Timestamp;

public class ChatGroupDTO {
    private int id;
    private String groupName;
    private Timestamp lastModified;
    private boolean isGroup;
    private String lastMessage;
    private Timestamp lastMessageTime;
    private String avatarUrl;

    public ChatGroupDTO(int id, String groupName, boolean isGroup, Timestamp lastModified, String lastMessage, Timestamp lastMessageTime, String avatarUrl) {
        this.id = id;
        this.groupName = groupName;
        this.isGroup = isGroup;
        this.lastModified = lastModified;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
        this.avatarUrl = avatarUrl;
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

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Timestamp getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(Timestamp lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
