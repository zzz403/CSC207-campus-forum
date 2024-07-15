package com.imperial.academia.use_case.chat;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.service.ChatGroupService;
import com.imperial.academia.session.SessionManager;

public class ChatCoordinatorInteractor implements ChatCoordinatorInputBoundary{
    private final ChatSideBarInputBoundary chatSidebarInteractor;
    private final ChatWindowInputBoundary chatWindowInteractor;
    private final ChatGroupService chatGroupService;
    private final ViewManagerModel viewManagerModel;

    public ChatCoordinatorInteractor(ChatSideBarInputBoundary chatSidebarInteractor,
                                     ChatWindowInputBoundary chatWindowInteractor, ViewManagerModel viewManagerModel) {
        this.chatSidebarInteractor = chatSidebarInteractor;
        this.chatWindowInteractor = chatWindowInteractor;
        this.viewManagerModel = viewManagerModel;
        this.chatGroupService = ServiceFactory.getChatGroupService();
    }

    @Override
    public void toPrivateChat(int userId) {
        try {
            int chatGroupId = chatGroupService.getPrivateChatId(SessionManager.getCurrentUser().getId(), userId);
            chatSidebarInteractor.execute();
            ChatWindowInputData chatWindowInputData = new ChatWindowInputData(chatGroupId,
                    "Hi, nice to meet you I'm" + SessionManager.getCurrentUser().getUsername(), "text");
            chatWindowInteractor.sendMessage(chatWindowInputData);
            chatWindowInteractor.execute(chatGroupId);
            viewManagerModel.setActiveView("chat");
            viewManagerModel.firePropertyChanged();
        } catch (Exception e) {
            System.out.println("An error occurred while loading chat messages.");
        }
    }
}
