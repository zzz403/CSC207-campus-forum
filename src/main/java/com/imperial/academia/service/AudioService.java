package com.imperial.academia.service;

import com.imperial.academia.entity.chat_message.WaveformData;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * The AudioService interface defines methods for audio recording, playback,
 * and processing within the chat application.
 */
public interface AudioService {

    /**
     * Starts audio recording for a specific chat group.
     *
     * @param chatGroupId The ID of the chat group for which the audio is being recorded.
     * @throws LineUnavailableException If the audio line cannot be opened due to resource restrictions.
     */
    void startRecording(int chatGroupId) throws LineUnavailableException;

    /**
     * Stops the current audio recording.
     */
    void stopRecording();

    /**
     * Loads and plays an audio file from the specified path.
     *
     * @param audioPath The path to the audio file to be loaded.
     */
    void loadAudio(String audioPath);

    /**
     * Returns the output file path for the recorded audio.
     *
     * @return The output file path as a String.
     */
    String getOutputFilePath();

    /**
     * Processes the audio file at the specified path and generates waveform data.
     *
     * @param audioPath The path to the audio file to be processed.
     * @return A WaveformData object containing the waveform data of the audio file.
     * @throws IOException If an I/O error occurs while reading the audio file.
     * @throws UnsupportedAudioFileException If the specified audio file is not supported.
     */
    WaveformData processAudio(String audioPath) throws IOException, UnsupportedAudioFileException;
}
