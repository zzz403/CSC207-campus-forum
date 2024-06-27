package com.imperial.academia.use_case.chat;

public interface ChatWindowInputBoundary {
    void execute(ChatWindowInputData chatWindowInputData);
    void startRecording(ChatWindowInputData chatWindowInputData);
    void stopRecording();
    void loadAudio(ChatWindowInputData chatWindowInputData);
}
