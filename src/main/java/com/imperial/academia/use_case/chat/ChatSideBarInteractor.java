package com.imperial.academia.use_case.chat;

import java.util.List;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.entity.chat_group.ChatGroupDTO;
import com.imperial.academia.service.ChatGroupService;

/**
 * Interactor class for handling chat sidebar operations.
 * Implements the ChatSideBarInputBoundary interface to process input data.
 */
public class ChatSideBarInteractor implements ChatSideBarInputBoundary {
    private final ChatGroupService chatGroupService;
    private final ChatSideBarOutputBoundary chatSideBarPresenter;

    /**
     * Constructor for ChatSideBarInteractor.
     *
     * @param chatPresenter the presenter for chat sidebar output
     */
    public ChatSideBarInteractor(ChatSideBarOutputBoundary chatPresenter) {
        this.chatGroupService = ServiceFactory.getChatGroupService();
        this.chatSideBarPresenter = chatPresenter;
    }

    /**
     * for unit test
     */
    public ChatSideBarInteractor(ChatSideBarOutputBoundary chatPresenter, ChatGroupService chatGroupService) {
        this.chatGroupService = chatGroupService;
        this.chatSideBarPresenter = chatPresenter;
    }

    /**
     * Executes the chat sidebar operation with the given input data.
     *
     * @param chatSideBarInputData the input data for the chat sidebar operation
     */
    @Override
    public void execute(ChatSideBarInputData chatSideBarInputData) {
        String searchQuery = chatSideBarInputData.getSearchQuery();
        try {
            List<ChatGroupDTO> chatGroups = chatGroupService.getChatGroupsByGroupName(searchQuery);
            if (chatGroups.isEmpty()) {
                chatSideBarPresenter.presentError("No chat groups found.");
            } else {
                ChatSideBarOutputData chatSideBarOutputData = new ChatSideBarOutputData(chatGroups);
                chatSideBarPresenter.presentChatGroups(chatSideBarOutputData);
            }
        } catch (Exception e) {
            chatSideBarPresenter.presentError("An error occurred while searching for chat groups.");
        }
    }

    public void execute() {
        String searchQuery = "";
        try {
            List<ChatGroupDTO> chatGroups = chatGroupService.getChatGroupsByGroupName(searchQuery);
            if (chatGroups.isEmpty()) {
                chatSideBarPresenter.presentError("No chat groups found.");
            } else {
                ChatSideBarOutputData chatSideBarOutputData = new ChatSideBarOutputData(chatGroups);
                chatSideBarPresenter.presentChatGroups(chatSideBarOutputData);
            }
        } catch (Exception e) {
            chatSideBarPresenter.presentError("An error occurred while searching for chat groups.");
        }
    }
}
