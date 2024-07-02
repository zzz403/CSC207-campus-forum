package com.imperial.academia.interface_adapter.chat;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.imperial.academia.entity.chat_group.ChatGroupDTO;
import com.imperial.academia.interface_adapter.common.ViewModel;

/**
 * The ChatSideBarViewModel class manages the state of the chat sidebar
 * and notifies listeners of any property changes.
 */
public class ChatSideBarViewModel extends ViewModel {
    private ChatSideBarState chatSideBarState = new ChatSideBarState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a ChatSideBarViewModel with a default name.
     */
    public ChatSideBarViewModel() {
        super("chat side bar");
    }

    /**
     * Returns the current state of the chat sidebar.
     *
     * @return The current ChatSideBarState.
     */
    public ChatSideBarState getState() {
        return chatSideBarState;
    }

    /**
     * Sets the state of the chat sidebar and notifies listeners of the change.
     *
     * @param chatSideBarState The new ChatSideBarState.
     */
    public void setState(ChatSideBarState chatSideBarState) {
        this.chatSideBarState = chatSideBarState;
        support.firePropertyChange("chatSideBarState", null, chatSideBarState);
    }

    /**
     * Adds a chat group to the state and notifies listeners of the change.
     *
     * @param chatGroupDTO The ChatGroupDTO to be added.
     */
    public void addChatGroup(ChatGroupDTO chatGroupDTO) {
        chatSideBarState.getChatGroups().add(chatGroupDTO);
        support.firePropertyChange("chatGroups", null, chatSideBarState.getChatGroups());
    }

    /**
     * Removes a chat group from the state and notifies listeners of the change.
     *
     * @param chatGroupDTO The ChatGroupDTO to be removed.
     */
    public void removeChatGroup(ChatGroupDTO chatGroupDTO) {
        chatSideBarState.getChatGroups().remove(chatGroupDTO);
        support.firePropertyChange("chatGroups", null, chatSideBarState.getChatGroups());
    }

    /**
     * Fires a property change event to notify listeners of any change to the chat sidebar state.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("chatSideBarState", null, chatSideBarState);
    }

    /**
     * Adds a PropertyChangeListener to listen for changes to the chat sidebar state.
     *
     * @param listener The PropertyChangeListener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
