package com.imperial.academia.use_case.chat;

public class ChatWindowInputData {
    private final int chatGroupId;
    private final String content;
    private final String contentType;

    public ChatWindowInputData(int chatGroupId, String content, String contentType) {
        this.chatGroupId = chatGroupId;
        this.content = content;
        this.contentType = contentType;
    }

    public int getChatGroupId() {
        return chatGroupId;
    }

    public String getContent() {
        return content;
    }

    public String getContentType() {
        return contentType;
    }
}
