package com.imperial.academia.service;

import javax.sound.sampled.LineUnavailableException;

public interface AudioService {
    void startRecording(int chatGroupId) throws LineUnavailableException;
    void stopRecording();
    void loadAudio(String audioPath);
    String getOutputFilePath();
}

