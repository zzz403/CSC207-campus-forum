package com.imperial.academia.use_case.chat;

public class ChatWindowInputData {
    private final int chatGroupId;
    private final String audioPath;

    public ChatWindowInputData(int chatGroupId) {
        this.chatGroupId = chatGroupId;
        this.audioPath = null;
    }

    public ChatWindowInputData(String audioPath) {
        this.chatGroupId = -1;
        this.audioPath = audioPath;
    }

    public ChatWindowInputData(int chatGroupId, String audioPath) {
        this.chatGroupId = chatGroupId;
        this.audioPath = audioPath;
    }

    public int getChatGroupId() {
        return chatGroupId;
    }

    public String getAudioPath() {
        return audioPath;
    }
}
