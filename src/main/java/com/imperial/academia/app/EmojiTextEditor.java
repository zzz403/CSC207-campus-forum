package com.imperial.academia.app;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class EmojiTextEditor extends JFrame {
    private JTextPane textPane;
    private JPopupMenu emojiMenu;

    public EmojiTextEditor() {
        textPane = new JTextPane();
        textPane.setPreferredSize(new Dimension(400, 300));
        JScrollPane scrollPane = new JScrollPane(textPane);

        // Create emoji menu
        emojiMenu = new JPopupMenu();
        String[] emojis = {"😊", "😂", "❤️", "👍", "😢"};
        for (String emoji : emojis) {
            JMenuItem item = new JMenuItem(emoji);
            item.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    insertEmoji(emoji);
                }
            });
            emojiMenu.add(item);
        }

        JButton emojiButton = new JButton("Insert Emoji");
        emojiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emojiMenu.show(emojiButton, emojiButton.getWidth() / 2, emojiButton.getHeight() / 2);
                textPane.requestFocusInWindow(); // 将焦点返回到文本框
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(emojiButton, BorderLayout.SOUTH);

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void insertEmoji(String emoji) {
        StyledDocument doc = textPane.getStyledDocument();
        try {
            doc.insertString(textPane.getCaretPosition(), emoji, null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        textPane.requestFocusInWindow(); // 确保插入表情符号后文本框获取焦点
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmojiTextEditor());
    }
}

