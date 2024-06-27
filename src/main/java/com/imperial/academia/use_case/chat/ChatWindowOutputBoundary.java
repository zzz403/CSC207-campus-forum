package com.imperial.academia.use_case.chat;

public interface ChatWindowOutputBoundary {
    void presentChatMessages(ChatWindowOutputData chatWindowOutputData);
    void presentError(String error);
}
