// ChatView.java
package com.imperial.academia.view;

import com.imperial.academia.view.components.ChatSideBarView;
import com.imperial.academia.view.components.ChatWindowView;

import javax.swing.*;
import java.awt.*;

public class ChatView extends JPanel {
    public final String viewName = "chat";

    public ChatView(ChatSideBarView chatSideBarView, ChatWindowView chatWindowView) {
        setLayout(new BorderLayout());

        add(chatSideBarView, BorderLayout.WEST);
        add(chatWindowView, BorderLayout.CENTER);
    }
}
