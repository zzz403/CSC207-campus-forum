package com.imperial.academia.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

class WaveformPanel extends JPanel {
    private final List<Integer> maxValues;
    private final int height;
    private final JButton playButton;

    public WaveformPanel(List<Integer> maxValues, int height) {
        this.maxValues = maxValues;
        this.height = height;
        int width = maxValues.size() * 4 + 230; // 每个maxValues的宽度为6像素，增加100像素宽度用于播放按钮和留白
        setPreferredSize(new Dimension(width, height + 20)); // 增加高度用于居中

        setBackground(new Color(0, 0, 0, 0)); // 透明背景以便绘制圆角矩形

        // 设置播放按钮

        ImageIcon playIcon = new ImageIcon("resources/play_icon_light.png");
        playIcon.setImage(playIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        playButton = new JButton(playIcon);
        playButton.setPreferredSize(new Dimension(25, 25));
        playButton.setBorderPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.setFocusPainted(false);
        playButton.setOpaque(false);
        playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        setLayout(null); // 使用null布局，以便手动设置播放按钮的位置
        playButton.setBounds(5, (height - 30) / 2 + 10, 30, 30); // 手动设置按钮位置
        add(playButton);
    }

    public void addPlayButtonActionListener(ActionListener listener) {
        playButton.addActionListener(listener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw rounded rectangle background
        int rectHeight = height - 10;
        int rectY = (getHeight() - rectHeight) / 2;
        g2.setColor(new Color(52, 152, 219));
        g2.fillRoundRect(0, rectY, getWidth(), rectHeight, 25, 25);

        int barWidth = 4; // Fixed width
        int barSpacing = 6; // Increased spacing between dots
        int centerY = getHeight() / 2;
        int threshold = 225; // Threshold to distinguish between dots and rounded rectangles

        for (int i = 0; i < maxValues.size(); i++) {
            int value = maxValues.get(i);
            if (value < threshold) {
                // Draw dot
                g2.setColor(Color.WHITE);
                g2.fillOval(40 + i * (barWidth + barSpacing), centerY - 2, barWidth, barWidth);
            } else {
                // Draw rounded rectangle
                int barHeight = Math.min((value * rectHeight) / 700, (int) (rectHeight * 0.9));
                int arcSize = 3;
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(40 + i * (barWidth + barSpacing), centerY - barHeight / 2, barWidth, barHeight, arcSize, arcSize);
            }
        }

        g2.dispose();
    }
}
