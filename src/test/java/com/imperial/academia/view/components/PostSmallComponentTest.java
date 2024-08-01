package com.imperial.academia.view.components;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PostSmallComponentTest {

    @Test
    public void testPostSmallComponentInitialization() throws IOException {
        // 创建测试数据
        BufferedImage image = ImageIO.read(new File("resources/test_image/test_image_1.jpg"));
        BufferedImage avatar = ImageIO.read(new File("resources/avatar/avatarExample.png"));
        String title = "Sample Title";
        String content = "This is a sample content for testing.";
        String author = "Author Name";
        int likes = 42;

        // 创建 PostSmallComponent 实例
        PostSmallComponent postSmallComponent = new PostSmallComponent(image, avatar, title, content, author, likes, new JFrame());

        // 验证组件不为 null
        assertNotNull(postSmallComponent);

        // 验证组件的布局和边框
        assertEquals(BorderLayout.class, postSmallComponent.getLayout().getClass());
        assertNotNull(postSmallComponent.getBorder());

        // 获取并验证组件的数量
        Component[] components = postSmallComponent.getComponents();
        assertEquals(2, components.length);

        // 验证 mapPlaceholder 标签
        assertNotNull(components[0]);
        assertEquals(JLabel.class, components[0].getClass());

        // 验证 infoPanel 面板
        assertNotNull(components[1]);
        assertEquals(JPanel.class, components[1].getClass());

        // 获取并验证 infoPanel 的组件数量
        JPanel infoPanel = (JPanel) components[1];
        Component[] infoComponents = infoPanel.getComponents();
        assertEquals(5, infoComponents.length);
    }
}
