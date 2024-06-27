package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.use_case.chat.ChatSideBarInputBoundary;
import com.imperial.academia.use_case.chat.ChatSideBarInputData;
import com.imperial.academia.use_case.chat.ChatWindowInputBoundary;
import com.imperial.academia.use_case.chat.ChatWindowInputData;

public class ChatSideBarController {
    private final ChatSideBarInputBoundary chatSideBarInteractor;
    private final ChatWindowInputBoundary chatWindowInteractor;

    public ChatSideBarController(ChatSideBarInputBoundary chatSideBarInteractor, ChatWindowInputBoundary chatWindowInteractor) {
        this.chatSideBarInteractor = chatSideBarInteractor;
        this.chatWindowInteractor = chatWindowInteractor;
    }

    public void execute(String chatGroupName) {
        ChatSideBarInputData chatSideBarInputData = new ChatSideBarInputData(chatGroupName);
        chatSideBarInteractor.execute(chatSideBarInputData);
    }

    public void selectChatGroup(int chatGroupId) {
        ChatWindowInputData windowInputData = new ChatWindowInputData(chatGroupId);
        chatWindowInteractor.execute(windowInputData);
    }
}
