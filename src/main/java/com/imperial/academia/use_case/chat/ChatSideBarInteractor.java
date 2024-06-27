package com.imperial.academia.use_case.chat;

import com.imperial.academia.service.ChatGroupService;

import java.util.List;

import com.imperial.academia.entity.chat_group.ChatGroupDTO;

public class ChatSideBarInteractor implements ChatSideBarInputBoundary{
    private ChatGroupService chatGroupService;
    private ChatSideBarOutputBoundary chatSideBarPresenter;

    public ChatSideBarInteractor(ChatGroupService chatGroupService, ChatSideBarOutputBoundary chatPresenter) {
        this.chatGroupService = chatGroupService;
        this.chatSideBarPresenter = chatPresenter;
    }

    public void execute(ChatSideBarInputData chatSideBarInputData) {
        String searchQuery = chatSideBarInputData.getSearchQuery();
        try {
            List<ChatGroupDTO> chatGroups = chatGroupService.getChatGroupsByGroupName(searchQuery);
            if(chatGroups.isEmpty()){
                chatSideBarPresenter.presentError("No chat groups found.");
            }else{
                ChatSideBarOutputData chatSideBarOutputData = new ChatSideBarOutputData(chatGroups);
                chatSideBarPresenter.presentChatGroups(chatSideBarOutputData);
            }
        } catch (Exception e) {
            chatSideBarPresenter.presentError("An error occurred while searching for chat groups.");
        }
    }
}
