package com.imperial.academia.use_case.chat;

import com.imperial.academia.entity.chat_message.ChatMessageDTO;
import java.util.List;

/**
 * The ChatWindowOutputData class encapsulates the data to be presented in the chat window,
 * specifically a list of chat messages and the chat group ID.
 */
public class ChatWindowOutputData {
    private final List<ChatMessageDTO> chatMessages;
    private final int chatGroupId;

    /**
     * Constructs a ChatWindowOutputData with the specified chat messages and chat group ID.
     *
     * @param chatMessages The list of ChatMessageDTO objects to be presented in the chat window.
     * @param chatGroupId The ID of the chat group to which the messages belong.
     */
    public ChatWindowOutputData(List<ChatMessageDTO> chatMessages, int chatGroupId) {
        this.chatMessages = chatMessages;
        this.chatGroupId = chatGroupId;
    }

    /**
     * Returns the list of chat messages.
     *
     * @return The list of ChatMessageDTO objects.
     */
    public List<ChatMessageDTO> getChatMessages() {
        return chatMessages;
    }

    /**
     * Returns the ID of the chat group to which the messages belong.
     *
     * @return The chat group ID.
     */
    public int getChatGroupId() {
        return chatGroupId;
    }
}
