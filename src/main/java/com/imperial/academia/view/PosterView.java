package com.imperial.academia.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.imperial.academia.interface_adapter.poster.PosterController;
import com.imperial.academia.interface_adapter.poster.PosterPresenter;
import com.imperial.academia.interface_adapter.poster.PosterViewModel;

public class PosterView extends JPanel {
    public final String viewName = "poster";
    public PosterView(PosterViewModel posterViewModel, PosterController posterController,PosterPresenter posterPresenter) {
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        mainPanel.setPreferredSize(new Dimension(400, 600));
    }
}
