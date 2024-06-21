package com.imperial.academia.entity.chat_message;

import java.sql.Timestamp;

public class ChatMessage {
    private int id;
    private int senderId;
    private int recipientId;
    private Integer groupId;
    private String content;
    private Timestamp timestamp;

    public ChatMessage(int id, int senderId, int recipientId, Integer groupId, String content, Timestamp timestamp) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.groupId = groupId;
        this.content = content;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getSenderId() { return senderId; }
    public void setSenderId(int senderId) { this.senderId = senderId; }

    public int getRecipientId() { return recipientId; }
    public void setRecipientId(int recipientId) { this.recipientId = recipientId; }

    public Integer getGroupId() { return groupId; }
    public void setGroupId(Integer groupId) { this.groupId = groupId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Timestamp getTimestamp() { return timestamp; }
    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }
}
