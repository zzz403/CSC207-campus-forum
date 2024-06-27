package com.imperial.academia.entity.chat_message;

public interface ChatMessageFactory {
    ChatMessage createChatMessage(int senderId, int recipientId, Integer groupId, String contentType, String content);
}
