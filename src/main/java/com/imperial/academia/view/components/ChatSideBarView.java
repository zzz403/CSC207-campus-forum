package com.imperial.academia.view.components;

import com.imperial.academia.interface_adapter.chat.ChatSideBarController;
import com.imperial.academia.interface_adapter.chat.ChatSideBarViewModel;
import com.imperial.academia.entity.chat_group.ChatGroupDTO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChatSideBarView extends JPanel{
    private JPanel chatListPanel;

    public ChatSideBarView(ChatSideBarController chatSideBarController,ChatSideBarViewModel chatSideBarViewModel) {
        setLayout(new BorderLayout());
        
        chatListPanel = new JPanel();
        chatListPanel.setLayout(new BoxLayout(chatListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(chatListPanel);
        add(scrollPane, BorderLayout.CENTER);

        chatSideBarViewModel.addPropertyChangeListener(evt -> {
            if (evt.getPropertyName().equals("chatSideBarState")) {
                displayChatGroups(chatSideBarViewModel.getState().getChatGroups());
            }
        });
        System.out.println("success in ChatSideBarView");
    }

    public void displayChatGroups(List<ChatGroupDTO> chatGroups) {
        chatListPanel.removeAll();
        for (ChatGroupDTO chatGroup : chatGroups) {
            JButton chatButton = new JButton(chatGroup.getGroupName());
            chatButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            chatButton.addActionListener(e -> {
                // Handle chat group selection
                // For example: controller.loadChat(chatGroup.getId());
            });
            chatListPanel.add(chatButton);
        }
        revalidate();
        repaint();
    }
}
