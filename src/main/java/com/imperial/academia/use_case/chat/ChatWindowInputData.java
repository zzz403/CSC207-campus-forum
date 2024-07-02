package com.imperial.academia.use_case.chat;

/**
 * The ChatWindowInputData class encapsulates the data required to send a message
 * in a chat window, including the chat group ID, message content, and content type.
 */
public class ChatWindowInputData {
    private final int chatGroupId;
    private final String content;
    private final String contentType;

    /**
     * Constructs a ChatWindowInputData with the specified parameters.
     *
     * @param chatGroupId The ID of the chat group to which the message belongs.
     * @param content The content of the message.
     * @param contentType The type of content (e.g., text, image, audio).
     */
    public ChatWindowInputData(int chatGroupId, String content, String contentType) {
        this.chatGroupId = chatGroupId;
        this.content = content;
        this.contentType = contentType;
    }

    /**
     * Returns the ID of the chat group to which the message belongs.
     *
     * @return The chat group ID.
     */
    public int getChatGroupId() {
        return chatGroupId;
    }

    /**
     * Returns the content of the message.
     *
     * @return The message content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Returns the type of content (e.g., text, image, audio).
     *
     * @return The content type.
     */
    public String getContentType() {
        return contentType;
    }
}
