package com.imperial.academia.use_case.chat;

public interface ChatSideBarOutputBoundary {

    void presentChatGroups(ChatSideBarOutputData chatSideBarOutputData);
    void presentError(String error);

}