package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.entity.chat_message.ChatMessageDTO;

import java.util.ArrayList;
import java.util.List;

public class ChatWindowState {
    private List<ChatMessageDTO> chatMessages = new ArrayList<>();
    private int chatGroupId;
    private String error = "";

    public ChatWindowState(ChatWindowState chatWindowState) {
        this.chatMessages = chatWindowState.getChatMessages();
        this.chatGroupId = chatWindowState.getChatGroupId();
        this.error = chatWindowState.getError();
    }

    public ChatWindowState() {
    }

    public List<ChatMessageDTO> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<ChatMessageDTO> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getChatGroupId() {
        return chatGroupId;
    }

    public void setChatGroupId(int chatGroupId) {
        this.chatGroupId = chatGroupId;
    }
}
