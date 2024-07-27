package com.imperial.academia.view.style;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CustomScrollBarUI extends BasicScrollBarUI {

    private static final int SCROLLBAR_WIDTH = 8; // Set the desired scrollbar width here

    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = new Color(128, 128, 128, 150);
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        // No need to paint the track
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Paint the thumb
        g2.setColor(thumbColor);
        g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);

        g2.dispose();
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createEmptyButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createEmptyButton();
    }

    private JButton createEmptyButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        button.setMinimumSize(new Dimension(0, 0));
        button.setMaximumSize(new Dimension(0, 0));
        return button;
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        // Adjust the preferred size based on the scrollbar orientation
        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            return new Dimension(SCROLLBAR_WIDTH, 100);
        } else {
            return new Dimension(100, SCROLLBAR_WIDTH);
        }
    }

    @Override
    protected void layoutVScrollbar(JScrollBar sb) {
        super.layoutVScrollbar(sb);
        sb.setPreferredSize(new Dimension(SCROLLBAR_WIDTH, sb.getHeight()));
    }

    @Override
    protected void layoutHScrollbar(JScrollBar sb) {
        super.layoutHScrollbar(sb);
        sb.setPreferredSize(new Dimension(sb.getWidth(), SCROLLBAR_WIDTH));
    }

    // Custom JScrollPane to apply the CustomScrollBarUI
    public static class CustomScrollPane extends JScrollPane {
        public CustomScrollPane(Component view) {
            super(view);
            customizeScrollBars();
        }

        private void customizeScrollBars() {
            JScrollBar verticalScrollBar = getVerticalScrollBar();
            JScrollBar horizontalScrollBar = getHorizontalScrollBar();
            verticalScrollBar.setUI(new CustomScrollBarUI());
            horizontalScrollBar.setUI(new CustomScrollBarUI());
        }
    }
}
