package com.imperial.academia.frontend;

import javax.swing.*;
import java.awt.*;

public class ForumPanel extends JPanel {
    public ForumPanel() {
        setLayout(new BorderLayout());

        JTextArea forumTextArea = new JTextArea();
        forumTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(forumTextArea);

        add(scrollPane, BorderLayout.CENTER);

        JTextField inputField = new JTextField();
        add(inputField, BorderLayout.SOUTH);

        inputField.addActionListener(e -> {
            String message = inputField.getText();
            forumTextArea.append("You: " + message + "\n");
            inputField.setText("");
        });
    }
}
