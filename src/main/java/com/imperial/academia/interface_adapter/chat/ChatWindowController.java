package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.use_case.chat.ChatWindowInputBoundary;
import com.imperial.academia.use_case.chat.ChatWindowInputData;

public class ChatWindowController {
    private final ChatWindowInputBoundary chatWindowInteractor;

    public ChatWindowController(ChatWindowInputBoundary chatWindowInteractor) {
        this.chatWindowInteractor = chatWindowInteractor;
    }

    public void loadChatMessages(int chatGroupId) {
        
        chatWindowInteractor.execute(chatGroupId);
    }

    public void sendMessage(String content, String contentType, int groupId) {
        ChatWindowInputData chatWindowInputData = new ChatWindowInputData(groupId, content, contentType);
        chatWindowInteractor.sendMessage(chatWindowInputData);
    }

    public void startRecording(int chatGroupId) {
        chatWindowInteractor.startRecording(chatGroupId);
    }

    public void stopRecording() {
        chatWindowInteractor.stopRecording();
    }

    public void loadAudio(String audioPath) {
        chatWindowInteractor.loadAudio(audioPath);
    }
}
