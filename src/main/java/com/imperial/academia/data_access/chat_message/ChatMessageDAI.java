package com.imperial.academia.data_access.chat_message;

import com.imperial.academia.entity.ChatMessage;

import java.sql.SQLException;
import java.util.List;

public interface ChatMessageDAI {
    void insert(ChatMessage chatMessage) throws SQLException;

    ChatMessage get(int id) throws SQLException;

    List<ChatMessage> getAll() throws SQLException;

    void update(ChatMessage chatMessage) throws SQLException;

    void delete(int id) throws SQLException;
}
