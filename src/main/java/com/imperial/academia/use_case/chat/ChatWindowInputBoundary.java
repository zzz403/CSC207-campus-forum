package com.imperial.academia.use_case.chat;

public interface ChatWindowInputBoundary {
    void execute(int chatGroupId);
    void startRecording(int chatGroupId);
    void stopRecording();
    void loadAudio(String audioPath);
    void sendMessage(ChatWindowInputData chatWindowInputData);
}
