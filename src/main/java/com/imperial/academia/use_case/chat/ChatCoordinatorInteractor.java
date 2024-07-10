package com.imperial.academia.use_case.chat;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.service.ChatGroupService;
import com.imperial.academia.session.SessionManager;

public class ChatCoordinatorInteractor implements ChatCoordinatorInputBoundary{
    private final ChatSideBarInputBoundary chatSidebarInteractor;
    private final ChatWindowInputBoundary chatWindowInteractor;
    private final ChatGroupService chatGroupService;

    public ChatCoordinatorInteractor(ChatSideBarInputBoundary chatSidebarInteractor, ChatWindowInputBoundary chatWindowInteractor) {
        this.chatSidebarInteractor = chatSidebarInteractor;
        this.chatWindowInteractor = chatWindowInteractor;
        this.chatGroupService = ServiceFactory.getChatGroupService();
    }

    @Override
    public void toPrivateChat(int userId) {
        try {
            int chatGroupId = chatGroupService.getPrivateChatId(SessionManager.getCurrentUser().getId(), userId);
            chatSidebarInteractor.execute();
            chatWindowInteractor.execute(chatGroupId);
        } catch (Exception e) {
            System.out.println("An error occurred while loading chat messages.");
        }
    }
}
