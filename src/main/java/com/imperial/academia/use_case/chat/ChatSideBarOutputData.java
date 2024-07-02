package com.imperial.academia.use_case.chat;

import java.util.List;
import com.imperial.academia.entity.chat_group.ChatGroupDTO;

/**
 * The ChatSideBarOutputData class encapsulates the data to be presented in the chat sidebar,
 * specifically a list of chat groups.
 */
public class ChatSideBarOutputData {
    private final List<ChatGroupDTO> chatGroups;

    /**
     * Constructs a ChatSideBarOutputData with the specified list of chat groups.
     *
     * @param chatGroups The list of ChatGroupDTO objects to be presented in the chat sidebar.
     */
    public ChatSideBarOutputData(List<ChatGroupDTO> chatGroups) {
        this.chatGroups = chatGroups;
    }

    /**
     * Returns the list of chat groups.
     *
     * @return The list of ChatGroupDTO objects.
     */
    public List<ChatGroupDTO> getChatGroups() {
        return chatGroups;
    }
}
