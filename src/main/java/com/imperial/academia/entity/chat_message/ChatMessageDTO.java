package com.imperial.academia.entity.chat_message;

import java.sql.Timestamp;

/**
 * The ChatMessageDTO class represents a data transfer object for chat messages,
 * containing information about the message, its sender, and associated metadata.
 */
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
    private MapData mapData;

    /**
     * Constructs a ChatMessageDTO with the specified parameters.
     *
     * @param id The ID of the chat message.
     * @param senderId The ID of the sender.
     * @param senderName The name of the sender.
     * @param senderAvatarUrl The URL of the sender's avatar.
     * @param groupId The ID of the group (if any) the message belongs to.
     * @param contentType The type of content in the message (e.g., text, image, audio).
     * @param content The content of the message.
     * @param isMe Boolean indicating if the current user is the sender of the message.
     * @param timestamp The timestamp of when the message was sent.
     */
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
        this.waveformData = null;
        this.mapData = null;
    }

    /**
     * Returns the ID of the chat message.
     *
     * @return The ID of the chat message.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the chat message.
     *
     * @param id The ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the ID of the sender.
     *
     * @return The ID of the sender.
     */
    public int getSenderId() {
        return senderId;
    }

    /**
     * Sets the ID of the sender.
     *
     * @param senderId The ID to set.
     */
    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    /**
     * Returns the name of the sender.
     *
     * @return The name of the sender.
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * Sets the name of the sender.
     *
     * @param senderName The name to set.
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    /**
     * Returns the URL of the sender's avatar.
     *
     * @return The URL of the sender's avatar.
     */
    public String getSenderAvatarUrl() {
        return senderAvatarUrl;
    }

    /**
     * Sets the URL of the sender's avatar.
     *
     * @param senderAvatarUrl The URL to set.
     */
    public void setSenderAvatarUrl(String senderAvatarUrl) {
        this.senderAvatarUrl = senderAvatarUrl;
    }

    /**
     * Returns the ID of the group the message belongs to (if any).
     *
     * @return The group ID or null if the message is not part of a group.
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * Sets the ID of the group the message belongs to.
     *
     * @param groupId The group ID to set.
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * Returns the content type of the message.
     *
     * @return The content type of the message.
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Sets the content type of the message.
     *
     * @param contentType The content type to set.
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Returns the content of the message.
     *
     * @return The content of the message.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the message.
     *
     * @param content The content to set.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Returns whether the current user is the sender of the message.
     *
     * @return True if the current user is the sender, false otherwise.
     */
    public boolean isMe() {
        return isMe;
    }

    /**
     * Sets whether the current user is the sender of the message.
     *
     * @param isMe True if the current user is the sender, false otherwise.
     */
    public void setMe(boolean isMe) {
        this.isMe = isMe;
    }

    /**
     * Returns the timestamp of when the message was sent.
     *
     * @return The timestamp of the message.
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of when the message was sent.
     *
     * @param timestamp The timestamp to set.
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Returns the waveform data associated with the message (if any).
     *
     * @return The waveform data or null if there is no waveform data.
     */
    public WaveformData getWaveformData() {
        return waveformData;
    }

    /**
     * Sets the waveform data associated with the message.
     *
     * @param waveformData The waveform data to set.
     */
    public void setWaveformData(WaveformData waveformData) {
        this.waveformData = waveformData;
    }

    /**
     * Returns the map data associated with the message (if any).
     *
     * @return The map data or null if there is no map data.
     */
    public MapData getMapData() {
        return mapData;
    }

    /**
     * Sets the map data associated with the message.
     *
     * @param mapData The map data to set.
     */
    public void setMapData(MapData mapData) {
        this.mapData = mapData;
    }
}
