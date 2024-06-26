package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.entity.chat_message.ChatMessageDTO;
import com.imperial.academia.interface_adapter.common.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChatWindowViewModel extends ViewModel {
    private ChatWindowState chatWindowState = new ChatWindowState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ChatWindowViewModel() {
        super("chat window");
    }

    public ChatWindowState getState() {
        return new ChatWindowState(chatWindowState);
    }

    public void setState(ChatWindowState chatWindowState) {
        this.chatWindowState = chatWindowState;
        support.firePropertyChange("chatWindowState", null, chatWindowState);
    }

    public void addChatMessage(ChatMessageDTO chatMessageDTO) {
        chatWindowState.getChatMessages().add(chatMessageDTO);
        support.firePropertyChange("chatMessages", null, chatWindowState.getChatMessages());
    }

    public void removeChatMessage(ChatMessageDTO chatMessageDTO) {
        chatWindowState.getChatMessages().remove(chatMessageDTO);
        support.firePropertyChange("chatMessages", null, chatWindowState.getChatMessages());
    }

    public void firePropertyChanged() {
        support.firePropertyChange("chatWindowState", null, chatWindowState);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
