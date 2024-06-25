package com.imperial.academia.view.components;

import com.imperial.academia.interface_adapter.chat.ChatWindowController;
import com.imperial.academia.interface_adapter.chat.ChatWindowViewModel;
import com.imperial.academia.entity.chat_message.ChatMessageDTO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChatWindowView extends JPanel {
    private JPanel messageListPanel;
    private JTextField messageInputField;

    public ChatWindowView(ChatWindowController chatWindowController, ChatWindowViewModel chatWindowViewModel) {
        setLayout(new BorderLayout());

        // Message list panel
        messageListPanel = new JPanel();
        messageListPanel.setLayout(new BoxLayout(messageListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(messageListPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Message input panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        messageInputField = new JTextField();
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> {
            String messageContent = messageInputField.getText();
            if (!messageContent.isEmpty()) {
                chatWindowController.sendMessage(messageContent);
                messageInputField.setText("");
            }
        });

        inputPanel.add(messageInputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
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
            messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
            JLabel senderLabel = new JLabel(chatMessage.getSenderName());
            JLabel messageContentLabel = new JLabel(chatMessage.getContent());
            messagePanel.add(senderLabel);
            messagePanel.add(messageContentLabel);
            messageListPanel.add(messagePanel);
        }
        revalidate();
        repaint();
    }
}
