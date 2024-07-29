package com.imperial.academia.use_case.ASR;

/**
 * ASRInputBoundary defines the interface for the Automatic Speech Recognition (ASR) use case.
 * Implementations of this interface should provide functionality to convert speech from an audio file to text.
 */
public interface ASRInputBoundary {

    /**
     * Converts speech from the provided audio file to text.
     *
     * @param audioFile The path to the audio file containing speech.
     * @return A String representing the recognized text from the audio file.
     */
    String speechToText(String audioFile);
}
