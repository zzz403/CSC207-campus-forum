package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.entity.chat_group.ChatGroupDTO;

import java.util.*;

/**
 * State class for the chat sidebar, managing the list of chat groups and any errors.
 */
public class ChatSideBarState {
    private List<ChatGroupDTO> chatGroups = new ArrayList<>();
    private String error = "";

    /**
     * Constructor for ChatSideBarState.
     * Initializes the chat groups list.
     */
    public ChatSideBarState() {
        chatGroups = new ArrayList<>();
    }

    /**
     * Gets the list of chat groups.
     *
     * @return a list of ChatGroupDTO objects
     */
    public List<ChatGroupDTO> getChatGroups() {
        return chatGroups;
    }

    /**
     * Sets the list of chat groups.
     *
     * @param chatGroups the list of ChatGroupDTO objects to set
     */
    public void setChatGroups(List<ChatGroupDTO> chatGroups) {
        this.chatGroups = chatGroups;
    }

    /**
     * Gets the error message.
     *
     * @return the error message as a string
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the error message.
     *
     * @param error the error message to set
     */
    public void setError(String error) {
        this.error = error;
    }
}
