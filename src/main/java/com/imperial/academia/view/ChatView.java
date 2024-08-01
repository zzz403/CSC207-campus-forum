    // ChatView.java
    package com.imperial.academia.view;

    import com.imperial.academia.view.components.ChatSideBarView;
    import com.imperial.academia.view.components.ChatWindowView;

    import javax.swing.*;
    import java.awt.*;

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
