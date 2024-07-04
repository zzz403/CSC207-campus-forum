package com.imperial.academia.entity.chat_message;

/**
 * Interface for creating chat messages.
 */
public interface ChatMessageFactory {

    /**
     * Creates a new chat message instance.
     *
     * @param senderId the ID of the sender
     * @param recipientId the ID of the recipient
     * @param groupId the ID of the group (can be null)
     * @param contentType the type of content (e.g., text, image, audio)
     * @param content the content of the message
     * @return a new instance of ChatMessage
     */
    ChatMessage createChatMessage(int senderId, int recipientId, Integer groupId, String contentType, String content);
}
