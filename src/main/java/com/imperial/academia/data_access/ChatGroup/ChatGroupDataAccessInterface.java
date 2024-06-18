package com.imperial.academia.data_access.ChatGroup;

import java.util.List;

import com.imperial.academia.entity.ChatGroup;

public interface ChatGroupDataAccessInterface {
    void save(ChatGroup chatGroup);
    List<ChatGroup> findAll();
}
