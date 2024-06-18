package com.imperial.academia.data_access.ChatMessage;

import java.util.List;

import com.imperial.academia.entity.ChatMessage;

public interface ChatMessageDataAccessInterface {
    void save(ChatMessage chatMessage);
    List<ChatMessage> findByGroupId(int groupId);
}
