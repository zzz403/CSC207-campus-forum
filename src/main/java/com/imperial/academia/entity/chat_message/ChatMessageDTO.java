package com.imperial.academia.entity.chat_message;

import java.sql.Timestamp;

public class ChatMessageDTO {
    private int id;
    private int senderId;
    private String senderName;
    private String senderAvatarUrl;
    private Integer groupId;
    private String contentType;
    private String content;
    private boolean isMe;
    private Timestamp timestamp;
    private WaveformData waveformData;

    public ChatMessageDTO(int id, int senderId, String senderName, String senderAvatarUrl, Integer groupId, String contentType, String content, boolean isMe, Timestamp timestamp) {
        this.id = id;
        this.senderId = senderId;
        this.senderName = senderName;
        this.senderAvatarUrl = senderAvatarUrl;
        this.groupId = groupId;
        this.contentType = contentType;
        this.content = content;
        this.isMe = isMe;
        this.timestamp = timestamp;
        waveformData = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderAvatarUrl() {
        return senderAvatarUrl;
    }

    public void setSenderAvatarUrl(String senderAvatarUrl) {
        this.senderAvatarUrl = senderAvatarUrl;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isMe() {
        return isMe;
    }

    public void setMe(boolean isMe) {
        this.isMe = isMe;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public WaveformData getWaveformData() {
        return waveformData;
    }

    public void setWaveformData(WaveformData waveformData) {
        this.waveformData = waveformData;
    }
    
}
