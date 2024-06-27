package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.use_case.chat.ChatWindowInputBoundary;
import com.imperial.academia.use_case.chat.ChatWindowInputData;

public class ChatWindowController {
    private final ChatWindowInputBoundary chatWindowInteractor;

    public ChatWindowController(ChatWindowInputBoundary chatWindowInteractor) {
        this.chatWindowInteractor = chatWindowInteractor;
    }

    public void loadChatMessages(int chatGroupId) {
        ChatWindowInputData chatWindowInputData = new ChatWindowInputData(chatGroupId);
        chatWindowInteractor.execute(chatWindowInputData);
    }

    public void sendMessage(String content) {
        // Implementation for sending a message
        // Use chatWindowInteractor to interact with the use case
    }

    public void startRecording(int chatGroupId) {
        ChatWindowInputData chatWindowInputData = new ChatWindowInputData(chatGroupId);
        chatWindowInteractor.startRecording(chatWindowInputData);
    }

    public void stopRecording() {
        chatWindowInteractor.stopRecording();
    }

    public void loadAudio(String audioPath) {
        ChatWindowInputData chatWindowInputData = new ChatWindowInputData(audioPath);
        chatWindowInteractor.loadAudio(chatWindowInputData);
    }
}
