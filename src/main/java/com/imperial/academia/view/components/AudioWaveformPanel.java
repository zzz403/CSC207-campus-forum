package com.imperial.academia.view.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class AudioWaveformPanel extends JPanel {
    private final List<Integer> minValues;
    private final List<Integer> maxValues;
    private final JButton playButton;
    private static Image scaledImage = null;

    private static final int THRESHOLD = 300;

    public AudioWaveformPanel(List<Integer> minValues, List<Integer> maxValues) {
        this.minValues = minValues;
        this.maxValues = maxValues;
        this.setPreferredSize(new Dimension(300, 50)); // 设置面板大小，根据需要调整
        this.playButton = new JButton();
        if (AudioWaveformPanel.scaledImage == null) {
            try {
                BufferedImage iconImage = ImageIO.read(new File("resources/play_icon_light.png"));
                AudioWaveformPanel.scaledImage = iconImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.playButton.setIcon(new ImageIcon(AudioWaveformPanel.scaledImage));
        this.playButton.setPreferredSize(new Dimension(30, 30)); // 设置按钮大小
        this.playButton.setContentAreaFilled(false); // 移除按钮背景
        this.playButton.setBorderPainted(false); // 移除按钮边框
        this.playButton.setFocusPainted(false); // 移除按钮焦点

        setLayout(new BorderLayout());
        add(playButton, BorderLayout.WEST);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int middle = height / 2;

        // 画圆角背景
        g2d.setColor(new Color(52, 152, 219)); // 设置背景颜色
        g2d.fillRoundRect(0, 0, width, height, 25, 25);

        // 设置波形颜色
        g2d.setColor(Color.WHITE);

        // 计算步长
        int step = Math.max(1, minValues.size() / (width - 40)); // 减去按钮宽度

        // 绘制波形
        for (int i = 0; i < (width - 40) && i * step < minValues.size(); i++) {
            int x = i + 40; // 给按钮留出空间
            int minValue = minValues.get(i * step);
            int maxValue = maxValues.get(i * step);

            // 根据阈值调整绘制值
            int adjustedMinValue = adjustValue(minValue);
            int adjustedMaxValue = adjustValue(maxValue);

            int yMin = middle - (int) ((adjustedMinValue / 1200.0) * middle);
            int yMax = middle - (int) ((adjustedMaxValue / 1200.0) * middle);

            g2d.drawLine(x, yMin, x, yMax);
        }

        g2d.dispose();
    }

    private int adjustValue(int value) {
        if (Math.abs(value) < THRESHOLD) {
            return 0;
        }
        return value;
    }

    public JButton getPlayButton() {
        return playButton;
    }
}

