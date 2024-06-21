package com.imperial.academia.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.imperial.academia.interface_adapter.postboard.PostBoardController;
import com.imperial.academia.interface_adapter.postboard.PostBoardPresenter;
import com.imperial.academia.interface_adapter.postboard.PostBoardViewModel;

public class PostBoardView extends JPanel {
    public final String viewName = "post board";
    public PostBoardView(PostBoardViewModel posterViewModel, PostBoardController posterController,PostBoardPresenter posterPresenter) {
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        mainPanel.setPreferredSize(new Dimension(400, 600));
    }
}
