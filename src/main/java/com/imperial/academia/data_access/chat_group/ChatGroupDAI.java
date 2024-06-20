package com.imperial.academia.data_access.chat_group;

import com.imperial.academia.entity.ChatGroup;

import java.sql.SQLException;
import java.util.List;

public interface ChatGroupDAI {
    void insert(ChatGroup chatGroup) throws SQLException;

    ChatGroup get(int id) throws SQLException;

    List<ChatGroup> getAll() throws SQLException;

    void update(ChatGroup chatGroup) throws SQLException;

    void delete(int id) throws SQLException;
}
