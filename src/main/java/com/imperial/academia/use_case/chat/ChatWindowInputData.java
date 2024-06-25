package com.imperial.academia.use_case.chat;

public class ChatWindowInputData {
    private final int chatGroupId;

    public ChatWindowInputData(int chatGroupId) {
        this.chatGroupId = chatGroupId;
    }

    public int getChatGroupId() {
        return chatGroupId;
    }
}
