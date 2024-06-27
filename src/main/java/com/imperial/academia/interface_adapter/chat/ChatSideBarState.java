package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.entity.chat_group.ChatGroupDTO;

import java.util.*;

public class ChatSideBarState {
    private List<ChatGroupDTO> chatGroups = new ArrayList<>();
    private String error = "";
    
    public ChatSideBarState() {
        chatGroups = new ArrayList<>();
    }

    public List<ChatGroupDTO> getChatGroups() {
        return chatGroups;
    }

    public void setChatGroups(List<ChatGroupDTO> chatGroups) {
        this.chatGroups = chatGroups;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
