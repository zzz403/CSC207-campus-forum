package com.imperial.academia.entity.chat_message;

import java.sql.Timestamp;

public class CommonChatMessageFactory implements ChatMessageFactory{
    @Override
    public ChatMessage createChatMessage(int senderId, int recipientId, Integer groupId, String contentType, String content) {
        return new ChatMessage(1, senderId, 1, groupId, contentType, content, new Timestamp(System.currentTimeMillis()));
    }
}
