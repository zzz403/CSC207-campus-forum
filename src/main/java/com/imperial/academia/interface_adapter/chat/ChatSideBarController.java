package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.use_case.chat.ChatSideBarInputBoundary;
import com.imperial.academia.use_case.chat.ChatSideBarInputData;
import com.imperial.academia.use_case.chat.ChatWindowInputBoundary;

/**
 * Controller for handling actions related to the chat sidebar.
 */
public class ChatSideBarController {
    private final ChatSideBarInputBoundary chatSideBarInteractor;
    private final ChatWindowInputBoundary chatWindowInteractor;

    /**
     * Constructor for ChatSideBarController.
     *
     * @param chatSideBarInteractor the interactor for chat sidebar operations
     * @param chatWindowInteractor the interactor for chat window operations
     */
    public ChatSideBarController(ChatSideBarInputBoundary chatSideBarInteractor, ChatWindowInputBoundary chatWindowInteractor) {
        this.chatSideBarInteractor = chatSideBarInteractor;
        this.chatWindowInteractor = chatWindowInteractor;
    }

    /**
     * Executes an action to handle a chat group by its name.
     *
     * @param chatGroupName the name of the chat group
     */
    public void execute(String chatGroupName) {
        ChatSideBarInputData chatSideBarInputData = new ChatSideBarInputData(chatGroupName);
        chatSideBarInteractor.execute(chatSideBarInputData);
    }

    /**
     * Selects a chat group by its ID and executes related actions in the chat window.
     *
     * @param chatGroupId the ID of the chat group
     */
    public void selectChatGroup(int chatGroupId) {
        chatWindowInteractor.execute(chatGroupId);
    }
}
