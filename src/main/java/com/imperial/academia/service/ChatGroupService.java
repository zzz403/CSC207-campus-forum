package com.imperial.academia.service;

import com.imperial.academia.entity.chat_group.ChatGroup;
import java.sql.SQLException;
import java.util.List;

public interface ChatGroupService {
    void insert(ChatGroup chatGroup) throws SQLException;

    ChatGroup get(int id) throws SQLException;

    List<ChatGroup> getAll() throws SQLException;

    void update(ChatGroup chatGroup) throws SQLException;

    void delete(int id) throws SQLException;
}
