package com.imperial.academia.use_case.chat;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;

public interface ChatWindowInputBoundary {
    void execute(int chatGroupId);
    void startRecording(int chatGroupId);
    void stopRecording(int chatGroupId);
    void loadAudio(String audioPath);
    void sendMessage(ChatWindowInputData chatWindowInputData) throws UnsupportedAudioFileException, IOException, SQLException;
}
