package com.imperial.academia.entity.chat_message;

import java.util.List;

public class WaveformData {
    public final List<Integer> minValues;
    public final List<Integer> maxValues;
    public final float duration;

    public WaveformData(List<Integer> minValues, List<Integer> maxValues, float duration) {
        this.minValues = minValues;
        this.maxValues = maxValues;
        this.duration = duration;
    }

    public List<Integer> getMinValues() {
        return minValues;
    }

    public List<Integer> getMaxValues() {
        return maxValues;
    }

    public float getDuration() {
        return duration;
    }

}
