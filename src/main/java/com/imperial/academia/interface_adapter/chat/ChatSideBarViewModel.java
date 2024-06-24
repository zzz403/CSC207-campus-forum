package com.imperial.academia.interface_adapter.chat;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.imperial.academia.entity.chat_group.ChatGroupDTO;
import com.imperial.academia.interface_adapter.common.ViewModel;

public class ChatSideBarViewModel extends ViewModel{
    private ChatSideBarState chatSideBarState = new ChatSideBarState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ChatSideBarViewModel() {
        super("chat side bar");
    }

    public ChatSideBarState getState() {
        return chatSideBarState;
    }

    public void setState(ChatSideBarState chatSideBarState) {
        this.chatSideBarState = chatSideBarState;
        support.firePropertyChange("chatSideBarState", null, chatSideBarState);
    }

    public void addChatGroup(ChatGroupDTO chatGroupDTO) {
        chatSideBarState.getChatGroups().add(chatGroupDTO);
        support.firePropertyChange("chatGroups", null, chatSideBarState.getChatGroups());
    }

    public void removeChatGroup(ChatGroupDTO chatGroupDTO) {
        chatSideBarState.getChatGroups().remove(chatGroupDTO);
        support.firePropertyChange("chatGroups", null, chatSideBarState.getChatGroups());
    }

    public void firePropertyChanged() {
        support.firePropertyChange("chatSideBarState", null, chatSideBarState);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
