package com.imperial.academia.view.components;

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
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ChatWindowView extends JPanel {
    private JPanel messageListPanel;
    private JTextField messageInputField;
    ChatWindowController chatWindowController;
    Image scaledOpenMicIconImage;
    Image scaledCloseMicIconImage;

    public ChatWindowView(ChatWindowController chatWindowController, ChatWindowViewModel chatWindowViewModel) {
        setLayout(new BorderLayout());

        this.chatWindowController = chatWindowController;

        // Message list panel
        messageListPanel = new JPanel();
        messageListPanel.setLayout(new BoxLayout(messageListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(messageListPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Message input panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(new Color(240, 240, 240)); // Set background color

        // Attachments and options panel
        JPanel attachmentsPanel = new JPanel();
        attachmentsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        attachmentsPanel.setOpaque(false); // Make panel transparent

        try {
            BufferedImage plusIconImage = ImageIO.read(new File("resources\\plus_icon.png"));
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

        try {
            BufferedImage smileyIconImage = ImageIO.read(new File("resources\\smiley_icon.png"));
            Image scaledSmileyIconImage = smileyIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            JLabel smileyIcon = new JLabel(new ImageIcon(scaledSmileyIconImage));
            optionsPanel.add(smileyIcon);

            BufferedImage micCloseIconImage = ImageIO.read(new File("resources\\mic_close_icon.png"));
            scaledCloseMicIconImage = micCloseIconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

            BufferedImage micOpenIconImage = ImageIO.read(new File("resources\\mic_open_icon.png"));
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

                    if (!recording) {
                        // Get the current group ID and sender info
                        int groupId = chatWindowViewModel.getState().getChatGroupId();

                        chatWindowController.startRecording(groupId);
                        recording = true;
                        micButton.setIcon(new ImageIcon(scaledOpenMicIconImage)); // Change icon if needed

                        // Start a timer to stop recording after 60 seconds
                        timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                if (recording) {
                                    chatWindowController.stopRecording();
                                    recording = false;
                                    micButton.setIcon(new ImageIcon(scaledCloseMicIconImage)); // Change icon if needed
                                }
                            }
                        }, 60000);
                    } else {
                        chatWindowController.stopRecording();
                        recording = false;
                        micButton.setIcon(new ImageIcon(scaledCloseMicIconImage)); // Change icon if needed
                        if (timer != null) {
                            timer.cancel(); // Cancel the timer if recording is stopped manually
                        }
                    }
                }
            });
            optionsPanel.add(micButton);

            BufferedImage cameraIconImage = ImageIO.read(new File("resources\\camera_icon.png"));
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

    public void displayChatMessages(List<ChatMessageDTO> chatMessages) {
        messageListPanel.removeAll();
        for (ChatMessageDTO chatMessage : chatMessages) {
            JPanel messagePanel = new JPanel();
            messagePanel.setLayout(new BorderLayout());
            messagePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            messagePanel.setBackground(new Color(245, 245, 245));
    
            // Avatar
            JLabel avatarLabel = new JLabel();
            try {
                BufferedImage avatarImage = ImageIO.read(new File(chatMessage.getSenderAvatarUrl()));
                Image scaledAvatarImage = avatarImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                avatarLabel.setIcon(new ImageIcon(scaledAvatarImage));
            } catch (IOException e) {
                e.printStackTrace();
            }
            avatarLabel.setBorder(new EmptyBorder(0, 0, 0, 10));
            messagePanel.add(avatarLabel, BorderLayout.WEST);
    
            // Message content
            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
            contentPanel.setBackground(new Color(245, 245, 245));
            
            JLabel senderLabel = new JLabel(chatMessage.getSenderName());
            senderLabel.setFont(new Font("Arial", Font.BOLD, 12));
            senderLabel.setForeground(new Color(44, 62, 80));
            contentPanel.add(senderLabel);
    
            if (chatMessage.getContentType().equals("text")) {
                JLabel messageContentLabel = new JLabel(chatMessage.getContent());
                messageContentLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                messageContentLabel.setOpaque(true);
                messageContentLabel.setBackground(new Color(52, 152, 219));
                messageContentLabel.setForeground(Color.WHITE);
                messageContentLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                contentPanel.add(messageContentLabel);
            } else if (chatMessage.getContentType().equals("image")) {
                JLabel messageImageLabel = new JLabel();
                BufferedImage messageImage;
                try {
                    messageImage = ImageIO.read(new File(chatMessage.getContent()));
                } catch (IOException e) {
                    messageImage = (BufferedImage) new ImageIcon("resources\\image_not_found.png").getImage();
                }
                Image scaledMessageImage = messageImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                messageImageLabel.setIcon(new ImageIcon(scaledMessageImage));
                /**
                 *                 
                 */
                contentPanel.add(messageImageLabel);
            } else if (chatMessage.getContentType().equals("audio")) {
                JButton playButton = new JButton("Play");
                System.out.println("audio file path: " + chatMessage.getContent());
                playButton.addActionListener(e -> chatWindowController.loadAudio(chatMessage.getContent()));
                contentPanel.add(playButton);
            }
    
            // Format timestamp
            String formattedTimestamp = formatTimestamp(chatMessage.getTimestamp());
            JLabel timeLabel = new JLabel(formattedTimestamp);
            timeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            timeLabel.setForeground(new Color(189, 195, 199));
            contentPanel.add(timeLabel);
    
            messagePanel.add(contentPanel, BorderLayout.CENTER);
            messageListPanel.add(messagePanel);
        }
        revalidate();
        repaint();
    }
    

    private String formatTimestamp(Timestamp timestamp) {
        Calendar messageTime = Calendar.getInstance();
        messageTime.setTimeInMillis(timestamp.getTime());

        Calendar now = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, h:mm a");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy MMM dd, h:mm a");

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
}
