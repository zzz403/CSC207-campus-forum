// ChatViewTest.java
package com.imperial.academia.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ChatViewTest {
    private JPanel chatSideBarView;
    private JPanel chatWindowView;
    private ChatView chatView;

    @BeforeEach
    public void setUp() {
        // Initialize mock JPanels
        chatSideBarView = new JPanel();
        chatWindowView = new JPanel();

        // Initialize ChatView with mock panels
        chatView = new ChatView(chatSideBarView, chatWindowView);
    }

    @Test
    public void testChatViewInitialization() {
        // Check that the ChatView is not null
        assertNotNull(chatView);

        // Check the layout of ChatView
        assertTrue(chatView.getLayout() instanceof BorderLayout);

        // Check that ChatSideBarView is added to the WEST
        assertEquals(chatSideBarView, ((BorderLayout) chatView.getLayout()).getLayoutComponent(BorderLayout.WEST));

        // Check that ChatWindowView is added to the CENTER
        assertEquals(chatWindowView, ((BorderLayout) chatView.getLayout()).getLayoutComponent(BorderLayout.CENTER));
    }
}
