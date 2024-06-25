package com.imperial.academia.use_case.chat;

import com.imperial.academia.entity.chat_message.ChatMessageDTO;

import java.util.List;

public class ChatWindowOutputData {
    private final List<ChatMessageDTO> chatMessages;

    public ChatWindowOutputData(List<ChatMessageDTO> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public List<ChatMessageDTO> getChatMessages() {
        return chatMessages;
    }
}
