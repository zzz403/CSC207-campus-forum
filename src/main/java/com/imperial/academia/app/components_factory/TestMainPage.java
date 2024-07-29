package com.imperial.academia.app.components_factory;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import com.imperial.academia.view.components.PostSmallComponent;

public class TestMainPage {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Map Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600); // 调整窗口大小以适应布局

            // 创建一个主面板并设置为 GridBagLayout
            JPanel mainPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.anchor = GridBagConstraints.NORTHWEST;

            // 使用工厂类创建多个 PostSmallComponent 实例并添加到主面板
            String imageUrl1 = "resources/test_image/test_image_1.jpg";
            String imageUrl2 = "resources/test_image/test_image_2.jpg";
            String avatarUrl = "resources/avatar/avatarExample.png";

            for (int i = 0; i < 8; i++) { // 例如创建8个组件
                PostSmallComponent postComponent = PostSmallComponentFactory.createPostSmallComponent(
                        (i % 2 == 0) ? imageUrl1 : imageUrl2,
                        avatarUrl,
                        "Title " + (i + 1),
                        "Content " + (i + 1),
                        "Author " + (i + 1),
                        10 * (i + 1),
                        frame
                );

                gbc.gridx = i % 2;
                gbc.gridy = i / 2;
                mainPanel.add(postComponent, gbc);
            }

            // 创建一个 JScrollPane 并将 mainPanel 添加到其中
            JScrollPane scrollPane = new JScrollPane(mainPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            frame.getContentPane().add(scrollPane);
            frame.setVisible(true);
        });
    }
}