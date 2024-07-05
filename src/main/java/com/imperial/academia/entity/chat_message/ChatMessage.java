package com.imperial.academia.entity.chat_message;

import java.sql.Timestamp;

/**
 * The ChatMessage class represents a chat message entity with relevant details.
 */
public class ChatMessage {
    private int id;
    private int senderId;
    private Integer groupId;
    private String contentType;
    private String content;
    private Timestamp timestamp;

    public ChatMessage(int id, int senderId, Integer groupId, String contentType, String content, Timestamp timestamp) {
        this.id = id;
        this.senderId = senderId;
        this.groupId = groupId;
        this.contentType = contentType;
        this.content = content;
        this.timestamp = timestamp;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier of the chat message.
     * 
     * @return The unique identifier of the chat message.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the chat message.
     * 
     * @param id The unique identifier of the chat message.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the user who sent the message.
     * 
     * @return The ID of the user who sent the message.
     */
    public int getSenderId() {
        return senderId;
    }

    /**
     * Sets the ID of the user who sent the message.
     * 
     * @param senderId The ID of the user who sent the message.
     */
    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    /**
     * Gets the ID of the group the message was sent to, if applicable.
     * 
     * @return The ID of the group the message was sent to, if applicable.
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * Sets the ID of the group the message was sent to, if applicable.
     * 
     * @param groupId The ID of the group the message was sent to, if applicable.
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * Gets the content of the chat message.
     * 
     * @return The content of the chat message.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the chat message.
     * 
     * @param content The content of the chat message.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the timestamp when the message was sent.
     * 
     * @return The timestamp when the message was sent.
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp when the message was sent.
     * 
     * @param timestamp The timestamp when the message was sent.
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }
}
