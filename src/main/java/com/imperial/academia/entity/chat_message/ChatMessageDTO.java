package com.imperial.academia.entity.chat_message;

import java.sql.Timestamp;

public class ChatMessageDTO {
    private int id;
    private int senderId;
    private String senderName;
    private String senderAvatarUrl;
    private Integer groupId;
    private String content;
    private Timestamp timestamp;

    public ChatMessageDTO(int id, int senderId, String senderName, String senderAvatarUrl, Integer groupId, String content, Timestamp timestamp) {
        this.id = id;
        this.senderId = senderId;
        this.senderName = senderName;
        this.senderAvatarUrl = senderAvatarUrl;
        this.groupId = groupId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public int getSenderId() {
        return senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getSenderAvatarUrl() {
        return senderAvatarUrl;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setSenderAvatarUrl(String senderAvatarUrl) {
        this.senderAvatarUrl = senderAvatarUrl;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    
}
