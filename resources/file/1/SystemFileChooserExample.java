package com.imperial.academia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SystemFileChooserExample extends JFrame {
    private JTextField filePathField;

    public SystemFileChooserExample() {
        setTitle("System File Chooser Example");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建面板
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 创建文件路径显示字段
        filePathField = new JTextField();
        filePathField.setEditable(false);
        filePathField.setPreferredSize(new Dimension(300, 30));
        panel.add(filePathField, BorderLayout.CENTER);

        // 创建上传按钮
        JButton uploadButton = new JButton("Upload File");
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog fileDialog = new FileDialog(SystemFileChooserExample.this, "Select a file", FileDialog.LOAD);
                fileDialog.setVisible(true);
                String filePath = fileDialog.getDirectory() + fileDialog.getFile();
                if (filePath != null && fileDialog.getFile() != null) {
                    filePathField.setText(filePath);
                    System.out.println("File selected: " + filePath);
                }
            }
        });
        panel.add(uploadButton, BorderLayout.EAST);

        // 将面板添加到框架
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SystemFileChooserExample());
    }
}


