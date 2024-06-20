package com.imperial.academia.style;

import javax.swing.*;
import java.awt.*;

public class UIStyle {

    public static final Color BACKGROUND_COLOR = new Color(240, 240, 255);
    public static final Color BUTTON_COLOR = new Color(70, 130, 180);
    public static final Color BUTTON_TEXT_COLOR = Color.WHITE;
    public static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 24);
    public static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 14);
    public static final Font LABEL_FONT = new Font("Arial", Font.PLAIN, 14);

    public static void applyBackground(JPanel panel) {
        panel.setBackground(BACKGROUND_COLOR);
    }

    public static void applyTitleLabel(JLabel label) {
        label.setFont(TITLE_FONT);
    }

    public static void applyLabel(JLabel label) {
        label.setFont(LABEL_FONT);
    }

    public static void styleButton(JButton button) {
        button.setPreferredSize(new Dimension(100, 40));
        button.setBackground(BUTTON_COLOR);
        button.setForeground(BUTTON_TEXT_COLOR);
        button.setFocusPainted(false);
        button.setFont(BUTTON_FONT);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
