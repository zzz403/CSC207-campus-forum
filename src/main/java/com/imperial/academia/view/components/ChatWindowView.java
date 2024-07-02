package com.imperial.academia.view.components;

import com.imperial.academia.app.components_factory.AvatarFactory;
import com.imperial.academia.entity.chat_message.WaveformData;
import com.imperial.academia.interface_adapter.chat.ChatWindowController;
import com.imperial.academia.interface_adapter.chat.ChatWindowViewModel;
import com.imperial.academia.entity.chat_message.ChatMessageDTO;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    ChatWindowController chatWindowController;
    Image scaledOpenMicIconImage;
    Image scaledCloseMicIconImage;

    /**
     * Constructor for ChatWindowView.
     * @param chatWindowController the chat window controller
     * @param chatWindowViewModel the chat window view model
     */
    public ChatWindowView(ChatWindowController chatWindowController, ChatWindowViewModel chatWindowViewModel) {
        setLayout(new BorderLayout());

        this.chatWindowController = chatWindowController;

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
        try {
            BufferedImage plusIconImage = ImageIO.read(new File("resources/plus_icon.png"));
            Image scaledPlusIconImage = plusIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            JLabel plusIcon = new JLabel(new ImageIcon(scaledPlusIconImage));
            attachmentsPanel.add(plusIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        inputPanel.add(attachmentsPanel, BorderLayout.WEST);

        // Message input field
        messageInputField = new JTextField();
        messageInputField.setFont(new Font("Arial", Font.PLAIN, 14));
        messageInputField.setBackground(new Color(245, 245, 245));
        messageInputField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        messageInputField.addActionListener(e -> {
            String messageContent = messageInputField.getText();
            int groupId = chatWindowViewModel.getState().getChatGroupId();
            if (!messageContent.isEmpty()) {
                chatWindowController.sendMessage(messageContent, "text", groupId);
                messageInputField.setText("");
            }
        });

        inputPanel.add(messageInputField, BorderLayout.CENTER);

        // Options icons panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        optionsPanel.setOpaque(false); // Make panel transparent

        // Adding option icons
        try {
            BufferedImage smileyIconImage = ImageIO.read(new File("resources/smiley_icon.png"));
            Image scaledSmileyIconImage = smileyIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            JLabel smileyIcon = new JLabel(new ImageIcon(scaledSmileyIconImage));
            optionsPanel.add(smileyIcon);

            BufferedImage micCloseIconImage = ImageIO.read(new File("resources/mic_close_icon.png"));
            scaledCloseMicIconImage = micCloseIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

            BufferedImage micOpenIconImage = ImageIO.read(new File("resources/mic_open_icon.png"));
            scaledOpenMicIconImage = micOpenIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

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

            BufferedImage cameraIconImage = ImageIO.read(new File("resources/camera_icon.png"));
            Image scaledCameraIconImage = cameraIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            JLabel cameraIcon = new JLabel(new ImageIcon(scaledCameraIconImage));
            optionsPanel.add(cameraIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
                    messageImage = (BufferedImage) new ImageIcon("resources/image_not_found.png").getImage();
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

                    contentPanel.add(waveformPanel);
                }
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
}
