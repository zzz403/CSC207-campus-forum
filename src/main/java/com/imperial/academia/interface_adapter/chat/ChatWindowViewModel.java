package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.entity.chat_message.ChatMessageDTO;
import com.imperial.academia.interface_adapter.common.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ChatWindowViewModel class manages the state of the chat window
 * and notifies listeners of any property changes.
 */
public class ChatWindowViewModel extends ViewModel {
    private ChatWindowState chatWindowState = new ChatWindowState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a ChatWindowViewModel with a default name.
     */
    public ChatWindowViewModel() {
        super("chat window");
    }

    /**
     * Returns the current state of the chat window.
     *
     * @return The current ChatWindowState.
     */
    public ChatWindowState getState() {
        return new ChatWindowState(chatWindowState);
    }

    /**
     * Sets the state of the chat window and notifies listeners of the change.
     *
     * @param chatWindowState The new ChatWindowState.
     */
    public void setState(ChatWindowState chatWindowState) {
        this.chatWindowState = chatWindowState;
        support.firePropertyChange("chatWindowState", null, chatWindowState);
    }

    /**
     * Adds a chat message to the state and notifies listeners of the change.
     *
     * @param chatMessageDTO The ChatMessageDTO to be added.
     */
    public void addChatMessage(ChatMessageDTO chatMessageDTO) {
        chatWindowState.getChatMessages().add(chatMessageDTO);
        support.firePropertyChange("chatMessages", null, chatWindowState.getChatMessages());
    }

    /**
     * Removes a chat message from the state and notifies listeners of the change.
     *
     * @param chatMessageDTO The ChatMessageDTO to be removed.
     */
    public void removeChatMessage(ChatMessageDTO chatMessageDTO) {
        chatWindowState.getChatMessages().remove(chatMessageDTO);
        support.firePropertyChange("chatMessages", null, chatWindowState.getChatMessages());
    }

    /**
     * Fires a property change event to notify listeners of any change to the chat window state.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("chatWindowState", null, chatWindowState);
    }

    /**
     * Fires a property change event to notify listeners of any change to the chat window state.
     *
     * @param propertyName The name of the property that has changed.
     */
    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, chatWindowState);
    }

    /**
     * Adds a PropertyChangeListener to listen for changes to the chat window state.
     *
     * @param listener The PropertyChangeListener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
