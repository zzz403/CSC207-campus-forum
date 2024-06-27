package com.imperial.academia.use_case.chat;

import com.imperial.academia.entity.chat_message.ChatMessageDTO;

import java.util.List;

public class ChatWindowOutputData {
    private final List<ChatMessageDTO> chatMessages;
    private final int chatGroupId;

    public ChatWindowOutputData(List<ChatMessageDTO> chatMessages, int chatGroupId) {
        this.chatMessages = chatMessages;
        this.chatGroupId = chatGroupId;
    }

    public List<ChatMessageDTO> getChatMessages() {
        return chatMessages;
    }

    public int getChatGroupId() {
        return chatGroupId;
    }
}
