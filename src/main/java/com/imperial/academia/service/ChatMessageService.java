package com.imperial.academia.service;

import com.imperial.academia.entity.chat_message.ChatMessage;
import java.sql.SQLException;
import java.util.List;

public interface ChatMessageService {
    void insert(ChatMessage chatMessage) throws SQLException;

    ChatMessage get(int id) throws SQLException;

    List<ChatMessage> getAll() throws SQLException;

    void update(ChatMessage chatMessage) throws SQLException;

    void delete(int id) throws SQLException;
}
