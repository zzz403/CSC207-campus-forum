package com.imperial.academia.view.components;

import com.imperial.academia.app.components_factory.AvatarFactory;
import com.imperial.academia.entity.chat_message.WaveformData;
import com.imperial.academia.interface_adapter.chat.ChatWindowController;
import com.imperial.academia.interface_adapter.chat.ChatWindowViewModel;
import com.imperial.academia.entity.chat_message.ChatMessageDTO;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.Timer;

/**
 * ChatWindowView class represents the chat window UI component.
 */
public class ChatWindowView extends JPanel {
    private final JPanel messageListPanel;
    private final JTextField messageInputField;
    private final ChatWindowController chatWindowController = new ChatWindowController();
    private final Map<String, BufferedImage> mapCache = new HashMap<>();
    
    private Image scaledOpenMicIconImage;
    private Image scaledCloseMicIconImage;

    /**
     * Constructor for ChatWindowView.
     * 
     * @param chatWindowViewModel the chat window view model
     * @param application the application window view model
     */
    public ChatWindowView(ChatWindowViewModel chatWindowViewModel, JFrame application) {
        setLayout(new BorderLayout());

        // Message list panel
        messageListPanel = new JPanel();
        messageListPanel.setLayout(new BoxLayout(messageListPanel, BoxLayout.Y_AXIS));
        messageListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(messageListPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        // Message input panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(new Color(240, 240, 240)); // Set background color

        // Attachments and options panel
        JPanel attachmentsPanel = new JPanel();
        attachmentsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        attachmentsPanel.setOpaque(false); // Make panel transparent

        // Adding attachment icon
        // Adding attachment icon
        try {
            BufferedImage plusIconImage = ImageIO.read(new File("resources/icons/plus_icon.png"));
            Image scaledPlusIconImage = plusIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            JLabel plusIcon = new JLabel(new ImageIcon(scaledPlusIconImage));
            attachmentsPanel.add(plusIcon);

            // Create RoundedPopupMenu
            RoundedPopupMenu popupMenu = new RoundedPopupMenu();
            popupMenu.setLayout(new GridLayout(2,1)); // 2 rows and 1 column
            popupMenu.setBorder(new LineBorder(Color.black, 4, true));

            // Load icons
            ImageIcon locationIcon = popupIconSize("resources/icons/location_icon.png", 30);
            ImageIcon reviewIcon = popupIconSize("resources/icons/ai_review_icon.png", 33);

            JPanel locationButton = new IconTextMenuItem(locationIcon, "send location");
            locationButton.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        int groupId = chatWindowViewModel.getState().getChatGroupId();
                        chatWindowController.sendLocation(groupId);
                    }
                }
            });

            // Add custom menu items
            popupMenu.add(locationButton);
            popupMenu.add(new IconTextMenuItem(reviewIcon, "AI review"));

            plusIcon.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
//                        Point location = plusIcon.getLocationOnScreen();
                        popupMenu.show(plusIcon, -20, -popupMenu.getPreferredSize().height - 15);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel spacerPanel = new JPanel();
        spacerPanel.setLayout(new BoxLayout(spacerPanel, BoxLayout.X_AXIS));
        spacerPanel.add(attachmentsPanel);
        spacerPanel.add(Box.createHorizontalStrut(10)); // spacer
        inputPanel.add(spacerPanel, BorderLayout.WEST);

        JPanel roundedPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // 绘制圆角矩形背景
                g2.setColor(new Color(245, 245, 245));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                // 绘制圆角矩形边框
                g2.setColor(Color.GRAY);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

                g2.dispose();
            }
        };
        roundedPanel.setLayout(new BorderLayout());
        roundedPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Message input field
        messageInputField = new JTextField();
        messageInputField.setFont(new Font("Arial", Font.PLAIN, 14));
        messageInputField.setBackground(new Color(245, 245, 245));
        messageInputField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        messageInputField.setPreferredSize(new Dimension(400, 30));
//        messageInputField.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        messageInputField.addActionListener(e -> {
            String messageContent = messageInputField.getText();
            int groupId = chatWindowViewModel.getState().getChatGroupId();
            if (!messageContent.isEmpty()) {
                chatWindowController.sendMessage(messageContent, "text", groupId);
                messageInputField.setText("");
            }
        });

        roundedPanel.add(messageInputField, BorderLayout.CENTER);

        inputPanel.add(roundedPanel, BorderLayout.CENTER);

        // Options icons panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        optionsPanel.setOpaque(false); // Make panel transparent

        optionsPanel.add(Box.createHorizontalStrut(5));

        // Adding option icons
        try {
            BufferedImage smileyIconImage = ImageIO.read(new File("resources/icons/smiley_icon.png"));
            Image scaledSmileyIconImage = smileyIconImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            JLabel smileyIcon = new JLabel(new ImageIcon(scaledSmileyIconImage));
            optionsPanel.add(smileyIcon);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load smiley icon");
        }

        optionsPanel.add(Box.createHorizontalStrut(5));
        try {
            BufferedImage micCloseIconImage = ImageIO.read(new File("resources/icons/mic_close_icon.png"));
            scaledCloseMicIconImage = micCloseIconImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);

            BufferedImage micOpenIconImage = ImageIO.read(new File("resources/icons/mic_open_icon.png"));
            scaledOpenMicIconImage = micOpenIconImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load mic icon");
        }

        JButton micButton = new JButton(new ImageIcon(scaledCloseMicIconImage));
        micButton.setBorder(BorderFactory.createEmptyBorder());
        micButton.setContentAreaFilled(false);
        micButton.addActionListener(new ActionListener() {
            private boolean recording = false;
            private Timer timer;
            private boolean isClickable = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isClickable) {
                    return;
                }
                isClickable = false;

                // Re-enable the button after 1 second
                TimerTask enableButtonTask = new TimerTask() {
                    @Override
                    public void run() {
                        isClickable = true;
                    }
                };
                new Timer().schedule(enableButtonTask, 1000);

                int groupId = chatWindowViewModel.getState().getChatGroupId();
                if (!recording) {
                    chatWindowController.startRecording(groupId);
                    recording = true;
                    micButton.setIcon(new ImageIcon(scaledOpenMicIconImage)); // Change icon if needed

                    // Start a timer to stop recording after 60 seconds
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if (recording) {
                                chatWindowController.stopRecording(groupId);
                                recording = false;
                                micButton.setIcon(new ImageIcon(scaledCloseMicIconImage)); // Change icon if needed
                            }
                        }
                    }, 60000);
                } else {
                    chatWindowController.stopRecording(groupId);
                    recording = false;
                    micButton.setIcon(new ImageIcon(scaledCloseMicIconImage)); // Change icon if needed
                    if (timer != null) {
                        timer.cancel(); // Cancel the timer if recording is stopped manually
                    }
                }
            }
        });
        optionsPanel.add(micButton);

        optionsPanel.add(Box.createHorizontalStrut(5));
        Image scaledSendFileIconImage = null;
        try {
            BufferedImage sendFileIconImage = ImageIO.read(new File("resources/icons/send_file.png"));
            scaledSendFileIconImage = sendFileIconImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load send file icon");
        }
        assert scaledSendFileIconImage != null;
        JLabel sendFileIcon = new JLabel(new ImageIcon(scaledSendFileIconImage));

        sendFileIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    // Open system file chooser dialog
                    ChatWindowView.this.setEnabled(false);
                    FileDialog fileDialog = new FileDialog(application, "Select a file");
                    fileDialog.setVisible(true);
                    String directory = fileDialog.getDirectory();
                    String file = fileDialog.getFile();
                    if (directory != null && file != null) {
                        File selectedFile = new File(directory, file);
                        // Send file to chatWindowController
                        int groupId = chatWindowViewModel.getState().getChatGroupId();
                        chatWindowController.sendFile(groupId, selectedFile);
                    }
                }
            }
        });

        optionsPanel.add(sendFileIcon);

        inputPanel.add(optionsPanel, BorderLayout.EAST);

        add(inputPanel, BorderLayout.SOUTH);

        // Listen to changes in the view model
        chatWindowViewModel.addPropertyChangeListener(evt -> {
            if (evt.getPropertyName().equals("chatWindowState")) {
                displayChatMessages(chatWindowViewModel.getState().getChatMessages());
            }
        });
    }

    /**
     * Display chat messages in the message list panel.
     * @param chatMessages the list of chat messages to display
     */
    public void displayChatMessages(List<ChatMessageDTO> chatMessages) {
        messageListPanel.removeAll();

        Timestamp lastTimestamp = null;
        Timestamp firstTimestamp = null;

        for (ChatMessageDTO chatMessage : chatMessages) {
            // Check if a new timestamp should be displayed
            Timestamp timestamp = chatMessage.getTimestamp();
            if (shouldShowTimestamp(timestamp, lastTimestamp, firstTimestamp)) {
                // Format timestamp
                String formattedTimestamp = formatTimestamp(chatMessage.getTimestamp());
                JLabel timeLabel = new JLabel(formattedTimestamp);
                timeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                timeLabel.setForeground(new Color(189, 195, 199));
                timeLabel.setHorizontalAlignment(SwingConstants.CENTER);

                JPanel timestampPanel = new JPanel();
                timestampPanel.setLayout(new BoxLayout(timestampPanel, BoxLayout.X_AXIS));
                timestampPanel.setBorder(new EmptyBorder(5, 0, 5, 0));
                timestampPanel.add(Box.createHorizontalGlue());
                timestampPanel.add(timeLabel);
                timestampPanel.add(Box.createHorizontalGlue());

                messageListPanel.add(timestampPanel);

                lastTimestamp = timestamp;
                firstTimestamp = timestamp;
            }

            JPanel messagePanel = new JPanel();
            messagePanel.setLayout(new BorderLayout());
            messagePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

            // Main content panel to hold avatar and message content
            JPanel mainContentPanel = new JPanel();
            mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.X_AXIS));

            if (!chatMessage.isMe()) {
                // Avatar
                AvatarComponent avatarLabel = AvatarFactory.create(chatMessage.getSenderId(), chatMessage.getSenderAvatarUrl(), 45);
                avatarLabel.setBorder(new EmptyBorder(0, 0, 0, 10));
                avatarLabel.setAlignmentY(Component.TOP_ALIGNMENT); // Align avatar to the top
                mainContentPanel.add(avatarLabel);

                mainContentPanel.add(Box.createHorizontalStrut(10));
            }

            // Message content
            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
            contentPanel.setAlignmentY(Component.TOP_ALIGNMENT); // Align content to the top

            if (!chatMessage.isMe()) {
                JLabel senderLabel = new JLabel(chatMessage.getSenderName());
                senderLabel.setFont(new Font("Arial", Font.BOLD, 12));
                senderLabel.setForeground(new Color(44, 62, 80));
                contentPanel.add(senderLabel);

                contentPanel.add(Box.createVerticalStrut(5));
            }

            if (chatMessage.getContentType().equals("text")) {
                JLabel messageContentLabel = createMessageContent(chatMessage);
                contentPanel.add(messageContentLabel);
            } else if (chatMessage.getContentType().equals("image")) {
                JLabel messageImageLabel = new JLabel();
                BufferedImage messageImage;
                try {
                    messageImage = ImageIO.read(new File(chatMessage.getContent()));
                } catch (IOException e) {
                    messageImage = (BufferedImage) new ImageIcon("resources/default/image_not_found.png").getImage();
                }
                Image scaledMessageImage = messageImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                messageImageLabel.setIcon(new ImageIcon(scaledMessageImage));
                contentPanel.add(messageImageLabel);
            } else if (chatMessage.getContentType().equals("audio")) {
                WaveformData waveformData = chatMessage.getWaveformData();
                if (waveformData != null) {
                    Component verticalStrut = Box.createVerticalStrut(-15); // Adjust this value as needed

                    contentPanel.add(verticalStrut);

                    WaveformPanel waveformPanel = new WaveformPanel(waveformData.getMaxValues(), waveformData.getDuration(), chatMessage.isMe(), 50); // Set height to 50
                    waveformPanel.addPlayButtonActionListener(e -> chatWindowController.loadAudio(chatMessage.getContent()));

                    JPanel outerPanel = new JPanel();
                    outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.Y_AXIS));
                    outerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
                    outerPanel.add(waveformPanel);

                    contentPanel.add(outerPanel);
                }
            } else if (chatMessage.getContentType().equals("map")) {
                BufferedImage mapImage;
                if (mapCache.containsKey(chatMessage.getContent())) {
                    mapImage = mapCache.get(chatMessage.getContent());
                } else {
                    try {
                        mapImage = ImageIO.read(new File(chatMessage.getContent()));
                        mapImage = mapResizeImage(mapImage, 300, 200); // Resize to desired dimensions
                        mapImage = mapMakeRoundedCorner(mapImage);
                        mapCache.put(chatMessage.getContent(), mapImage);
                    } catch (IOException e) {
                        mapImage = null;
                    }
                }

                MapPanel mapPanel = new MapPanel(chatMessage.getMapData(), mapImage, chatMessage.isMe());
                mapPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                mapPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (SwingUtilities.isLeftMouseButton(e)) {
                            double latitude = chatMessage.getMapData().getLatitude();
                            double longitude = chatMessage.getMapData().getLongitude();
                            String url = String.format("https://www.google.com/maps/search/?api=1&query=%s,%s", latitude, longitude);
                            try {
                                Desktop.getDesktop().browse(new URI(url));
                            } catch (IOException | URISyntaxException evt) {
                                evt.printStackTrace();
                            }
                        }
                    }
                });
                contentPanel.add(mapPanel);
            } else if (chatMessage.getContentType().equals("file")) {
                FilePanel filePanel = new FilePanel(chatMessage.getFileData(), chatMessage.isMe());
                filePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                filePanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        openFileLocation(chatMessage.getContent());
                    }
                });
                JPanel outerPanel = new JPanel();
                outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.Y_AXIS));
                outerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
                outerPanel.add(filePanel);

                contentPanel.add(outerPanel);
            }

            mainContentPanel.add(contentPanel);

            if (chatMessage.isMe()) {
                messagePanel.add(mainContentPanel, BorderLayout.EAST);
            } else {
                messagePanel.add(mainContentPanel, BorderLayout.WEST);
            }

            messageListPanel.add(messagePanel);
        }
        messageListPanel.revalidate();
        messageListPanel.repaint();

        // Scroll to bottom
        JScrollBar verticalScrollBar = ((JScrollPane) messageListPanel.getParent().getParent()).getVerticalScrollBar();
        SwingUtilities.invokeLater(() -> verticalScrollBar.setValue(verticalScrollBar.getMaximum()));
    }

    /**
     * Create a JLabel for the message content with custom rendering.
     * @param chatMessage the chat message DTO
     * @return a JLabel containing the message content
     */
    private JLabel createMessageContent(ChatMessageDTO chatMessage) {
        JLabel messageContentLabel = new JLabel(chatMessage.getContent()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw the rounded background
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

                // Draw the label text
                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += 10; // Add padding to width
                size.height += 10; // Add padding to height
                return size;
            }
        };

        messageContentLabel.setFont(new Font("Arial", Font.BOLD, 15));

        messageContentLabel.setOpaque(false); // We will paint the background ourselves
        messageContentLabel.setBackground(chatMessage.isMe() ? new Color(52, 152, 219) : Color.WHITE);
        messageContentLabel.setForeground(chatMessage.isMe() ? Color.WHITE : Color.BLACK);
        messageContentLabel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15)); // Add padding for text

        return messageContentLabel;
    }

    /**
     * Format the timestamp for display.
     * @param timestamp the timestamp to format
     * @return a formatted timestamp string
     */
    private String formatTimestamp(Timestamp timestamp) {
        Calendar messageTime = Calendar.getInstance();
        messageTime.setTimeInMillis(timestamp.getTime());

        Calendar now = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.ENGLISH);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, h:mm a", Locale.ENGLISH);
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy MMM dd, h:mm a", Locale.ENGLISH);

        if (now.get(Calendar.YEAR) == messageTime.get(Calendar.YEAR)) {
            if (now.get(Calendar.DAY_OF_YEAR) == messageTime.get(Calendar.DAY_OF_YEAR)) {
                return timeFormat.format(messageTime.getTime()); // Today
            } else if (now.get(Calendar.DAY_OF_YEAR) - messageTime.get(Calendar.DAY_OF_YEAR) == 1) {
                return "Yesterday, " + timeFormat.format(messageTime.getTime()); // Yesterday
            } else {
                return dateFormat.format(messageTime.getTime()); // This year but not today or yesterday
            }
        } else {
            return yearFormat.format(messageTime.getTime()); // Different year
        }
    }

    /**
     * Determine if a new timestamp should be shown.
     * @param currentTimestamp the current message timestamp
     * @param lastTimestamp the last message timestamp
     * @param firstTimestamp the first message timestamp
     * @return true if a new timestamp should be shown, false otherwise
     */
    private boolean shouldShowTimestamp(Timestamp currentTimestamp, Timestamp lastTimestamp, Timestamp firstTimestamp) {
        if (lastTimestamp == null || firstTimestamp == null) {
            return true;
        }

        // Calculate the difference in milliseconds
        long differenceSinceLast = currentTimestamp.getTime() - lastTimestamp.getTime();
        long differenceSinceFirst = currentTimestamp.getTime() - firstTimestamp.getTime();

        return differenceSinceLast > 300000 || differenceSinceFirst > 1200000;
    }

    private ImageIcon popupIconSize(String path, int size) {
        try {
            BufferedImage iconImage = ImageIO.read(new File(path));
            Image scaledIconImage = iconImage.getScaledInstance(size, size, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledIconImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private BufferedImage mapResizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(resultingImage, 0, 0, null);
        g2d.dispose();
        return outputImage;
    }

    private static BufferedImage mapMakeRoundedCorner(BufferedImage image) {
        int cornerRadius = 25; // The radius for the rounded corners
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        // Enable anti-aliasing for smooth corners
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);

        // Create a shape with rounded top corners and sharp bottom corners
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
        g2.fillRect(0, h / 2, w, h / 2);

        // Composite the image on top using the white shape as the alpha source
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        try {
            File outputFile = new File("rounded_corners_image.png");
            ImageIO.write(output, "png", outputFile);
            System.out.println("Saved rounded corners image to: " + outputFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output;
    }

    static class IconTextMenuItem extends JPanel {
        public IconTextMenuItem(ImageIcon icon, String text) {
            setLayout(new BorderLayout());
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JLabel iconLabel = new JLabel(icon);
            iconLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel textLabel = new JLabel(text);
            textLabel.setFont(new Font("Arial", Font.BOLD, 12));
            textLabel.setHorizontalAlignment(SwingConstants.CENTER);

            setCursor(new Cursor(Cursor.HAND_CURSOR));
            add(iconLabel, BorderLayout.CENTER);
            add(textLabel, BorderLayout.SOUTH);
        }
    }

    private void openFileLocation(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                JOptionPane.showMessageDialog(this, "file not exit: " + filePath, "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 获取文件的父目录
            File parentDir = file.getParentFile();
            if (parentDir == null) {
                JOptionPane.showMessageDialog(this, "can't get file path", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 使用 Desktop 类打开文件夹
            Desktop desktop = Desktop.getDesktop();
            desktop.open(parentDir);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "can't open " + filePath, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    static class RoundedPopupMenu extends JPopupMenu {

        public RoundedPopupMenu() {
            setOpaque(false);
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
    }
}
