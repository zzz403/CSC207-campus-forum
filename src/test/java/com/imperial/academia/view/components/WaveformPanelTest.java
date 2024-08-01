package com.imperial.academia.view.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class WaveformPanelTest {

    private WaveformPanel waveformPanel;

    @BeforeEach
    public void setUp() {
        waveformPanel = new WaveformPanel(Arrays.asList(100, 200, 300, 400, 500), 45.5f, true, 50);
    }

    @Test
    public void testWaveformPanelInitialization() {
        // 验证 WaveformPanel 不为 null
        assertNotNull(waveformPanel);

        // 验证布局和背景颜色
        assertNull(waveformPanel.getLayout()); // 使用 null 布局进行手动定位
        assertEquals(new Color(0, 0, 0, 0), waveformPanel.getBackground());

        // 验证首选大小
        Dimension expectedSize = new Dimension(145, 70); // 计算所得的首选大小
        assertEquals(expectedSize, waveformPanel.getPreferredSize());

        // 获取并验证 WaveformPanel 的子组件
        Component[] components = waveformPanel.getComponents();
        assertEquals(1, components.length);
        assertTrue(components[0] instanceof JButton);

        JButton playButton = (JButton) components[0];
        assertEquals(new Dimension(25, 25), playButton.getPreferredSize());
        assertFalse(playButton.isBorderPainted());
        assertFalse(playButton.isContentAreaFilled());
        assertFalse(playButton.isFocusPainted());
        assertFalse(playButton.isOpaque());
    }
}
