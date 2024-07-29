package com.imperial.academia.use_case.chat;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.service.ChatGroupService;
import com.imperial.academia.session.SessionManager;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;

/**
 * ChatCoordinatorInteractor is responsible for coordinating chat interactions,
 * including initializing private chats and managing the transition to the chat view.
 */
public class ChatCoordinatorInteractor implements ChatCoordinatorInputBoundary {
    private final ChatSideBarInputBoundary chatSidebarInteractor;
    private final ChatWindowInputBoundary chatWindowInteractor;
    private final ChatGroupService chatGroupService;
    private final ChangeViewInputBoundary changeViewInteractor = UsecaseFactory.getChangeViewInteractor();

    /**
     * Constructs a ChatCoordinatorInteractor and initializes the necessary interactors and services.
     */
    public ChatCoordinatorInteractor() {
        this.chatSidebarInteractor = UsecaseFactory.getChatSideBarInteractor();
        this.chatWindowInteractor = UsecaseFactory.getChatWindowInteractor();
        this.chatGroupService = ServiceFactory.getChatGroupService();
    }

    /**
     * Initiates a private chat with the specified user.
     *
     * @param userId The ID of the user to start a private chat with.
     */
    @Override
    public void toPrivateChat(int userId) {
        try {
            // Get the private chat ID between the current user and the specified user
            int chatGroupId = chatGroupService.getPrivateChatId(SessionManager.getCurrentUser().getId(), userId);

            // Execute sidebar interactor
            chatSidebarInteractor.execute();

            // Prepare chat window input data
            ChatWindowInputData chatWindowInputData = new ChatWindowInputData(
                    chatGroupId,
                    "Hi, nice to meet you! I'm " + SessionManager.getCurrentUser().getUsername(),
                    "text"
            );

            // Send message and execute chat window interactor
            chatWindowInteractor.sendMessage(chatWindowInputData);
            chatWindowInteractor.execute(chatGroupId);

            // Change view to the chat window
            changeViewInteractor.changeView("chat");
        } catch (Exception e) {
            System.out.println("An error occurred while loading chat messages.");
        }
    }
}
