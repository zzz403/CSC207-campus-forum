package com.imperial.academia.app;

import javax.swing.*;
import com.imperial.academia.view.MainFrame;
import java.util.Properties;

public class App {
    public static void main(String[] args) {
        // JTattoo
        try {
            Properties props = new Properties();
            // change style
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
    

/*
other style:

com.jtattoo.plaf.acryl.AcrylLookAndFeel.setCurrentTheme(props);
UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");

com.jtattoo.plaf.aero.AeroLookAndFeel.setCurrentTheme(props);
UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");

com.jtattoo.plaf.aluminium.AluminiumLookAndFeel.setCurrentTheme(props);
UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");

com.jtattoo.plaf.bernstein.BernsteinLookAndFeel.setCurrentTheme(props);
UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");

com.jtattoo.plaf.fast.FastLookAndFeel.setCurrentTheme(props);
UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");

com.jtattoo.plaf.graphite.GraphiteLookAndFeel.setCurrentTheme(props);
UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");

com.jtattoo.plaf.hifi.HiFiLookAndFeel.setCurrentTheme(props);
UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");

com.jtattoo.plaf.luna.LunaLookAndFeel.setCurrentTheme(props);
UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");

com.jtattoo.plaf.mcwin.McWinLookAndFeel.setCurrentTheme(props);
UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");

com.jtattoo.plaf.mint.MintLookAndFeel.setCurrentTheme(props);
UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");

com.jtattoo.plaf.noire.NoireLookAndFeel.setCurrentTheme(props);
UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");

com.jtattoo.plaf.smart.SmartLookAndFeel.setCurrentTheme(props);
UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");

com.jtattoo.plaf.texture.TextureLookAndFeel.setCurrentTheme(props);
UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");


*/