package com.imperial.academia.view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;

import com.imperial.academia.app.components_factory.AvatarFactory;
import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarController;
import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarState;
import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarViewModel;

/**
 * TopNavigationBar component class responsible for creating and managing all
 * components of the top navigation bar.
 */
public class TopNavigationBar extends JPanel {

    private final TopNavigationBarController topNavigationBarController = new TopNavigationBarController();

    private JPanel topNavPanel;
    private JLabel logoText;
    private JFrame applicationFrame;
    private JLabel profileButton;

    /**
     * Constructor to initialize the top navigation bar component.
     *
     * @param topNavigationBarViewModel  The top navigation bar view model.
     * @param applicationFrame           The main application frame.
     * @throws IOException If an error occurs while reading resources.
     */
    public TopNavigationBar(TopNavigationBarViewModel topNavigationBarViewModel, JFrame applicationFrame) throws IOException {
        this.applicationFrame = applicationFrame;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Create the main panel with GridBagLayout
        topNavPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw bottom border
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.fillRect(0, getHeight() - 1, getWidth(), 1);

                // Draw shadow
                g2d.setColor(new Color(0, 0, 0, 30));
                for (int i = 1; i <= 5; i++) {
                    g2d.drawLine(0, getHeight() + i - 1, getWidth(), getHeight() + i - 1);
                }

                g2d.dispose();
            }
        };
        topNavPanel.setBackground(Color.WHITE);
        topNavPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 20); // Add padding on the left side
        gbc.anchor = GridBagConstraints.WEST;

        // Logo and text as a button
        JButton logoButton = new JButton();
        logoButton.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0)); // Adjust padding
        logoButton.setBackground(Color.WHITE);
        logoButton.setBorderPainted(false);
        logoButton.setFocusPainted(false);
        logoButton.setContentAreaFilled(false);

        ImageIcon logoIcon = new ImageIcon("resources/logo.png");
        logoIcon.setImage(logoIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        JLabel logoLabel = new JLabel(logoIcon);

        logoText = new JLabel("Academia Imperial");
        logoText.setFont(new Font("Arial", Font.BOLD, 24));
        logoText.setForeground(new Color(130, 139, 150));

        logoButton.add(logoLabel);
        logoButton.add(logoText);
        logoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                topNavigationBarController.changeView("post board");
            }
        });
        logoButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        topNavPanel.add(logoButton, gbc);

        // Search Bar
        gbc.gridx++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        CustomSearchBar searchBar = new CustomSearchBar(20);
        searchBar.setMaximumSize(new Dimension(300, 30)); // Set max width to 300
        topNavPanel.add(searchBar, gbc);

        // Icons and Profile Picture
        gbc.gridx++;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setBackground(Color.WHITE);

        // Chat Button
        JButton chatButton = createIconButton("resources/icons/chat_icon.png", 40, 40, "Go to Chat");
        chatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                topNavigationBarController.changeView("chat");
            }
        });
        rightPanel.add(chatButton);

        // Add Create button
        JButton createButton = createTextIconButton("resources/icons/create_post_icon.png", "Create", "Create Post");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                topNavigationBarController.changeView("create post");
            }
        });
        rightPanel.add(createButton, gbc);

        // Notification Button
        JButton notificationButton = createIconButton("resources/icons/notification_icon.png", 40, 40, "Notifications");
        rightPanel.add(notificationButton);

        // Profile Button
        profileButton = new JLabel();
        profileButton.setPreferredSize(new Dimension(60, 60));
        profileButton.setBorder(new LineBorder(Color.WHITE, 1, true)); // Circular border

        rightPanel.add(profileButton);

        topNavPanel.add(rightPanel, gbc);

        // Add topNavPanel to this panel
        add(topNavPanel, BorderLayout.CENTER);

        topNavigationBarViewModel.addPropertyChangeListener(e -> {
            if ("state".equals(e.getPropertyName())) {
                TopNavigationBarState state = topNavigationBarViewModel.getState();
                String avatarUrlLambda = state.getAvatarUrl() != null ? state.getAvatarUrl()
                        : "resources/icons/default_profile_icon.png";
                rightPanel.remove(profileButton);

                // Create and add the new AvatarComponent
                profileButton = AvatarFactory.create(state.getUserId(), avatarUrlLambda, 60);
                profileButton.setPreferredSize(new Dimension(60, 60));
                profileButton.setBorder(new LineBorder(Color.WHITE, 1, true)); // Circular border

                rightPanel.add(profileButton);
                rightPanel.revalidate();
                rightPanel.repaint();
            }
        });

        applicationFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustLogoVisibility();
            }
        });

        adjustLogoVisibility();
    }

    /**
     * Creates a text and icon button with hover text.
     *
     * @param imagePath The path to the icon image.
     * @param text      The text to display on the button.
     * @param hoverText The text to display when the button is hovered over.
     * @return The created JButton.
     */
    private JButton createTextIconButton(String imagePath, String text, String hoverText) {
        ImageIcon icon = new ImageIcon(imagePath);
        icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));

        JButton button = new JButton();
        button.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0)); // Adjust padding
        button.setBackground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);

        JLabel iconLabel = new JLabel(icon);
        JLabel textLabel = new JLabel(text);
        textLabel.setFont(new Font("Arial", Font.BOLD, 20));
        textLabel.setForeground(Color.BLACK);

        button.add(iconLabel);
        button.add(textLabel);

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            private JWindow tooltipWindow;

            @Override
            public void mouseEntered(MouseEvent e) {
                tooltipWindow = new JWindow();
                tooltipWindow.setLayout(new BorderLayout());
                JPanel contentPanel = new JPanel();
                contentPanel.setBackground(Color.DARK_GRAY);
                contentPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                JLabel tooltipLabel = new JLabel(hoverText);
                tooltipLabel.setForeground(Color.WHITE);
                contentPanel.add(tooltipLabel);
                tooltipWindow.add(contentPanel);
                tooltipWindow.pack();
                Point location = button.getLocationOnScreen();
                tooltipWindow.setLocation(location.x + (button.getWidth() - tooltipWindow.getWidth()) / 2,
                        location.y + button.getHeight());
                tooltipWindow.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (tooltipWindow != null) {
                    tooltipWindow.setVisible(false);
                    tooltipWindow.dispose();
                }
            }
        });

        return button;
    }

    /**
     * Creates an icon button with hover text.
     *
     * @param imagePath The path to the icon image.
     * @param width     The width of the icon.
     * @param height    The height of the icon.
     * @param hoverText The text to display when the button is hovered over.
     * @return The created JButton.
     */
    private JButton createIconButton(String imagePath, int width, int height, String hoverText) {
        ImageIcon icon = new ImageIcon(imagePath);
        icon.setImage(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(width, height));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            private JWindow tooltipWindow;

            @Override
            public void mouseEntered(MouseEvent e) {
                tooltipWindow = new JWindow();
                tooltipWindow.setLayout(new BorderLayout());
                JPanel contentPanel = new JPanel();
                contentPanel.setBackground(Color.DARK_GRAY);
                contentPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                JLabel tooltipLabel = new JLabel(hoverText);
                tooltipLabel.setForeground(Color.WHITE);
                contentPanel.add(tooltipLabel);
                tooltipWindow.add(contentPanel);
                tooltipWindow.pack();
                Point location = button.getLocationOnScreen();
                tooltipWindow.setLocation(location.x + (button.getWidth() - tooltipWindow.getWidth()) / 2,
                        location.y + button.getHeight());
                tooltipWindow.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (tooltipWindow != null) {
                    tooltipWindow.setVisible(false);
                    tooltipWindow.dispose();
                }
            }
        });

        return button;
    }

    /**
     * Custom search bar with a search icon and placeholder text.
     */
    class CustomSearchBar extends JTextField {
        private final Icon searchIcon;

        public CustomSearchBar(int columns) {
            super(columns);
            Image searchImage = new ImageIcon("resources/icons/search_icon.png").getImage().getScaledInstance(20, 20,
                    Image.SCALE_SMOOTH);
            searchIcon = new ImageIcon(searchImage);
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 20));
            setBackground(new Color(240, 240, 240));
            setForeground(Color.GRAY);
            setText("Search...");
            addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (getText().equals("Search...")) {
                        setText("");
                        setForeground(Color.BLACK);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (getText().isEmpty()) {
                        setForeground(Color.GRAY);
                        setText("Search...");
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

            super.paintComponent(g);

            int iconHeight = searchIcon.getIconHeight();
            int x = 10;
            int y = (getHeight() - iconHeight) / 2;
            searchIcon.paintIcon(this, g2, x, y);

            g2.dispose();
        }

        @Override
        public void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
            g2.dispose();
        }
    }

    /**
     * Adjusts the visibility of the logo text based on the application frame width.
     */
    private void adjustLogoVisibility() {
        int frameWidth = applicationFrame.getWidth();
        if (frameWidth < 1000) {
            logoText.setVisible(false);
        } else {
            logoText.setVisible(false);
            logoText.setVisible(true);
        }
    }
}
