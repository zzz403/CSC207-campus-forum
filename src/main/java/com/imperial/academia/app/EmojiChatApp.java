package com.imperial.academia.app;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

    public class EmojiChatApp {
    private JFrame frame;
    private JTextPane chatPane;
    private JTextField inputField;
    private JButton sendButton;
    private JButton emojiButton;
    private JPanel panel;
    private ArrayList<String> messages; // 用于存储消息

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EmojiChatApp app = new EmojiChatApp();
            app.createAndShowGUI();
        });
    }

    public EmojiChatApp() {
        messages = new ArrayList<>();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Emoji Chat App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        chatPane = new JTextPane();
        chatPane.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(chatPane);

        inputField = new JTextField();
        sendButton = new JButton("Send");
        emojiButton = new JButton("😊");

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        emojiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertEmoji();
            }
        });

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(chatScrollPane, BorderLayout.CENTER);
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        inputPanel.add(emojiButton, BorderLayout.WEST);

        panel.add(inputPanel, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    private void sendMessage() {
        String text = inputField.getText();
        if (!text.isEmpty()) {
            appendToPane(chatPane, text, Color.BLACK);
            messages.add(text); // 将消息添加到存储中
            inputField.setText("");
        }
    }

    private void insertEmoji() {
        inputField.setText(inputField.getText() + "😊");
    }

    private void appendToPane(JTextPane tp, String msg, Color c) {
        StyledDocument doc = tp.getStyledDocument();

        Style style = tp.addStyle("I'm a Style", null);
        StyleConstants.setForeground(style, c);
        try {
            doc.insertString(doc.getLength(), msg + "\n", style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}
