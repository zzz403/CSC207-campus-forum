package com.imperial.academia.service;

import com.imperial.academia.entity.chat_message.WaveformData;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public interface AudioService {
    void startRecording(int chatGroupId) throws LineUnavailableException;
    void stopRecording();
    void loadAudio(String audioPath);
    String getOutputFilePath();
    WaveformData processAudio(String audioPath) throws IOException, UnsupportedAudioFileException;
}


