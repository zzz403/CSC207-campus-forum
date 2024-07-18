package com.imperial.academia.use_case.chat;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.service.ChatGroupService;
import com.imperial.academia.session.SessionManager;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;

public class ChatCoordinatorInteractor implements ChatCoordinatorInputBoundary{
    private final ChatSideBarInputBoundary chatSidebarInteractor;
    private final ChatWindowInputBoundary chatWindowInteractor;
    private final ChatGroupService chatGroupService;
    private final ChangeViewInputBoundary changeViewInteractor = UsecaseFactory.getChangeViewInteractor();

    public ChatCoordinatorInteractor() {
        this.chatSidebarInteractor = UsecaseFactory.getChatSideBarInteractor();
        this.chatWindowInteractor = UsecaseFactory.getChatWindowInteractor();
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
            changeViewInteractor.changeView("chat");
        } catch (Exception e) {
            System.out.println("An error occurred while loading chat messages.");
        }
    }
}
