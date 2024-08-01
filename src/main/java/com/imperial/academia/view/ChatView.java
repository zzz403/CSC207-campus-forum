// ChatView.java
package com.imperial.academia.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class ChatView extends JPanel {
    public final String viewName = "chat";

    JPanel chatSideBarView;
    JPanel chatWindowView;

    public ChatView(JPanel chatSideBarView, JPanel chatWindowView) {
        this.chatSideBarView = chatSideBarView;
        this.chatWindowView = chatWindowView;

        setLayout(new BorderLayout());

        add(chatSideBarView, BorderLayout.WEST);
        add(chatWindowView, BorderLayout.CENTER);
    }
}
