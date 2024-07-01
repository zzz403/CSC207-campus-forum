package com.imperial.academia.view.components;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AudioWaveformExample {
    public static void main(String[] args) {
        List<Integer> maxValues = // 从数据中获取最大值列表
                List.of(245, 77, 305, 309, 167, 297, 238, 34, 292, 282, 215, 215, 264, 278, 234, 206, 84, 234, 347, 199, 238, 482, 228, 48, 123,
                        287, 157, 217, 439, 444, 317, 348, 228, 16, 335, 329, 75, 431, 288, 367, 68, 83, 361, -5, 240, 477, 431, 302, 304, 118, 340, 410, 90, 261, 474, 493, -130, 244, 144, 262, 345, 121, 318, 314, 377, 461, 507, 228, 217, 183, 16, 107, 292, 319, 153,
                        220, 299, 313, 212, 119);

        JFrame frame = new JFrame("Audio Waveform Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        WaveformPanel waveformPanel = new WaveformPanel(maxValues, 400);
        frame.add(waveformPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}