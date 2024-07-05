package com.imperial.academia.entity.chat_message;

import java.sql.Timestamp;

/**
 * Factory class for creating common chat messages.
 */
public class CommonChatMessageFactory implements ChatMessageFactory {

    /**
     * Creates a new chat message instance with the given parameters.
     *
     * @param senderId    the ID of the sender
     * @param recipientId the ID of the recipient
     * @param groupId     the ID of the group (can be null)
     * @param contentType the type of content (e.g., text, image, audio)
     * @param content     the content of the message
     * @return a new instance of ChatMessage
     */
    @Override
    public ChatMessage createChatMessage(int senderId, int recipientId, Integer groupId, String contentType,
            String content) {
        return new ChatMessage(
                1, // Assuming the ID is generated elsewhere and is set to 1 here for simplicity
                senderId,
                groupId,
                contentType,
                content,
                new Timestamp(System.currentTimeMillis()) // Sets the current timestamp
        );
    }
}
