package com.imperial.academia.view;

import javax.swing.*;
import java.awt.*;

public class PanelController {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public PanelController(JPanel mainPanel, CardLayout cardLayout) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
    }

    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }
}
