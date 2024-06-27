package com.imperial.academia.use_case.chat;

import java.util.List;

import com.imperial.academia.entity.chat_group.ChatGroupDTO;

public class ChatSideBarOutputData {
    private final List<ChatGroupDTO> chatGroups;

    public ChatSideBarOutputData(List<ChatGroupDTO> chatGroups) {
        this.chatGroups = chatGroups;
    }

    public List<ChatGroupDTO> getChatGroups() {
        return chatGroups;
    }
}
