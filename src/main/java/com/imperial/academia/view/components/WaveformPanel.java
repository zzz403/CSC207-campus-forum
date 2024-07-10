package com.imperial.academia.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The WaveformPanel class is a custom JPanel that displays a visual representation
 * of an audio waveform along with a play button and the duration of the audio.
 */
class WaveformPanel extends JPanel {
    private final List<Integer> maxValues;
    private final int height;
    private final JButton playButton;
    private final boolean isMe;
    private final float duration;

    /**
     * Constructs a WaveformPanel with the specified parameters.
     *
     * @param maxValues List of maximum values representing the audio waveform.
     * @param duration Duration of the audio in seconds.
     * @param isMe Boolean indicating if the current user is the sender of the message.
     * @param height Height of the waveform panel.
     */
    public WaveformPanel(List<Integer> maxValues, float duration, boolean isMe, int height) {
        this.isMe = isMe;
        this.duration = duration;
        this.maxValues = maxValues;
        this.height = height;
        int width = maxValues.size() * (4 + 6) + 50 + 40; // Each maxValue width is 6 pixels, adding 90 pixels for play button and padding
        if (Math.round(duration) >= 10) {
            width += 5; // Adding extra width to display longer durations
        }
        setPreferredSize(new Dimension(width, height + 20)); // Increase height for centering

        setBackground(new Color(0, 0, 0, 0)); // Transparent background for rounded rectangle drawing

        // Set up play button
        ImageIcon playIcon = new ImageIcon(isMe ? "resources/icons/play_icon_light.png" : "resources/icons/play_icon_dark.png");
        playIcon.setImage(playIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        playButton = new JButton(playIcon);
        playButton.setPreferredSize(new Dimension(25, 25));
        playButton.setBorderPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.setFocusPainted(false);
        playButton.setOpaque(false);
        playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        setLayout(null); // Use null layout for manual positioning
        playButton.setBounds(5, (height - 30) / 2 + 10, 30, 30); // Manually set button position
        add(playButton);
    }

    /**
     * Adds an ActionListener to the play button.
     *
     * @param listener The ActionListener to be added.
     */
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
        g2.setColor(isMe ? new Color(52, 152, 219) : Color.WHITE);
        g2.fillRoundRect(0, rectY, getWidth(), rectHeight, 25, 25);

        int barWidth = 4; // Fixed width
        int barSpacing = 6; // Increased spacing between dots
        int centerY = getHeight() / 2;
        int threshold = 500; // Threshold to distinguish between dots and rounded rectangles

        for (int i = 0; i < maxValues.size(); i++) {
            int value = maxValues.get(i);
            g2.setColor(isMe ? Color.WHITE : Color.BLACK);
            if (value < threshold) {
                // Draw dot
                g2.fillOval(45 + i * (barWidth + barSpacing), centerY - 2, barWidth, barWidth);
            } else {
                // Draw rounded rectangle
                int barHeight = Math.min((value * rectHeight) / 3000, (int) (rectHeight * 0.6));
                int arcSize = 4;
                g2.fillRoundRect(45 + i * (barWidth + barSpacing), centerY - barHeight / 2, barWidth, barHeight, arcSize, arcSize);
            }
        }

        // Draw duration text
        int minutes = (int) duration / 60;
        int seconds = (int) duration % 60;
        String durationText = String.format("%d:%02d", minutes, seconds); // Format duration as 0:04
        g2.setFont(new Font("Arial", Font.BOLD, 12));
        g2.setColor(isMe ? Color.WHITE : Color.BLACK);
        g2.drawString(durationText, getWidth() - 35, centerY + 5); // Position at the end of waveform

        g2.dispose();
    }
}
