package com.imperial.academia.app;

import javax.swing.*;
import com.imperial.academia.frontend.MainFrame;
import java.util.Properties;

public class App {
    public static void main(String[] args) {
        // 设置JTattoo外观
        try {
            Properties props = new Properties();
            // 设置你想要的主题，这里以AcrylLookAndFeel为例
            props.put("logoString", "Academia Imperial");
            com.jtattoo.plaf.acryl.AcrylLookAndFeel.setCurrentTheme(props);
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
