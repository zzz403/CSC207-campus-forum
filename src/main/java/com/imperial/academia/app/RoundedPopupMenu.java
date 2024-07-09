package com.imperial.academia.app;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedPopupMenu extends JPopupMenu {

    public RoundedPopupMenu() {
        setOpaque(false); // Make the popup menu non-opaque
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int arc = 20; // Arc width and height for rounded corners
        Shape roundedRect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arc, arc);

        g2.setColor(getBackground());
        g2.fill(roundedRect);

        g2.setColor(getForeground());
        g2.draw(roundedRect);

        g2.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Rounded JPopupMenu Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLayout(new FlowLayout());

            JButton button = new JButton("Right Click Me");
            frame.add(button);

            RoundedPopupMenu popupMenu = new RoundedPopupMenu();
            JMenuItem item1 = new JMenuItem("Option 1");
            JMenuItem item2 = new JMenuItem("Option 2");
            JMenuItem item3 = new JMenuItem("Option 3");

            popupMenu.add(item1);
            popupMenu.add(item2);
            popupMenu.add(item3);

            button.setComponentPopupMenu(popupMenu);

            frame.setVisible(true);
        });
    }
}
