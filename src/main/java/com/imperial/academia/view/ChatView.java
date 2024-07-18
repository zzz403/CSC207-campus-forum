// ChatView.java
package com.imperial.academia.view;

import com.imperial.academia.view.components.ChatSideBarView;
import com.imperial.academia.view.components.ChatWindowView;

import javax.swing.*;
import java.awt.*;

public class ChatView extends JPanel {
    public final String viewName = "chat";

    ChatSideBarView chatSideBarView;
    ChatWindowView chatWindowView;

    public ChatView(ChatSideBarView chatSideBarView, ChatWindowView chatWindowView) {
        this.chatSideBarView = chatSideBarView;
        this.chatWindowView = chatWindowView;

        setLayout(new BorderLayout());

        add(chatSideBarView, BorderLayout.WEST);
        add(chatWindowView, BorderLayout.CENTER);
    }
}
