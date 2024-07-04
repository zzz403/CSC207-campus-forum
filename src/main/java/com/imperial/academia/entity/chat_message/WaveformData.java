package com.imperial.academia.entity.chat_message;

import java.util.List;

/**
 * Represents the waveform data of an audio message.
 */
public class WaveformData {
    // List of minimum values of the waveform
    public final List<Integer> minValues;
    // List of maximum values of the waveform
    public final List<Integer> maxValues;
    // Duration of the audio message
    public final float duration;

    /**
     * Constructor for WaveformData.
     *
     * @param minValues the minimum values of the waveform
     * @param maxValues the maximum values of the waveform
     * @param duration the duration of the audio message
     */
    public WaveformData(List<Integer> minValues, List<Integer> maxValues, float duration) {
        this.minValues = minValues;
        this.maxValues = maxValues;
        this.duration = duration;
    }

    /**
     * Gets the minimum values of the waveform.
     *
     * @return a list of minimum values
     */
    public List<Integer> getMinValues() {
        return minValues;
    }

    /**
     * Gets the maximum values of the waveform.
     *
     * @return a list of maximum values
     */
    public List<Integer> getMaxValues() {
        return maxValues;
    }

    /**
     * Gets the duration of the audio message.
     *
     * @return the duration in seconds
     */
    public float getDuration() {
        return duration;
    }
}
