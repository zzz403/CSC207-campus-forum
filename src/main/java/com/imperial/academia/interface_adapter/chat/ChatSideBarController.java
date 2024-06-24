package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.use_case.chat.ChatSideBarInputBoundary;
import com.imperial.academia.use_case.chat.ChatSideBarInputData;

public class ChatSideBarController {
    final ChatSideBarInputBoundary chatSideBarInteractor;

    public ChatSideBarController(ChatSideBarInputBoundary chatSideBarInteractor) {
        this.chatSideBarInteractor = chatSideBarInteractor;
    }

    public void execute(String chatGroupName) {
        ChatSideBarInputData chatSideBarInputData = new ChatSideBarInputData(chatGroupName);
        chatSideBarInteractor.execute(chatSideBarInputData);
    }
}
