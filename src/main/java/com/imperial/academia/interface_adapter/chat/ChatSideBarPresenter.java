package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.use_case.chat.ChatSideBarOutputData;
import com.imperial.academia.use_case.chat.ChatSideBarOutputBoundary;
import com.imperial.academia.entity.chat_group.ChatGroupDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * The ChatSideBarPresenter class implements the ChatSideBarOutputBoundary interface
 * and is responsible for updating the ChatSideBarViewModel with chat group data or errors.
 */
public class ChatSideBarPresenter implements ChatSideBarOutputBoundary {
    private final ChatSideBarViewModel chatSideBarViewModel;

    /**
     * Constructs a ChatSideBarPresenter with the specified view model.
     *
     * @param chatSideBarViewModel The view model to be updated by this presenter.
     */
    public ChatSideBarPresenter(ChatSideBarViewModel chatSideBarViewModel) {
        this.chatSideBarViewModel = chatSideBarViewModel;
    }

    /**
     * Updates the ChatSideBarViewModel with the provided chat groups.
     *
     * @param chatSideBarOutputData The data containing the list of chat groups.
     */
    public void presentChatGroups(ChatSideBarOutputData chatSideBarOutputData) {
        ChatSideBarState state = chatSideBarViewModel.getState();
        List<ChatGroupDTO> chatGroups = chatSideBarOutputData.getChatGroups();
        state.setChatGroups(chatGroups);
        chatSideBarViewModel.setState(state);

        chatSideBarViewModel.firePropertyChanged();
    }

    /**
     * Updates the ChatSideBarViewModel with an error message and clears the chat groups.
     *
     * @param error The error message to be displayed.
     */
    public void presentError(String error) {
        ChatSideBarState state = chatSideBarViewModel.getState();
        state.setError(error);
        state.setChatGroups(new ArrayList<>());
        chatSideBarViewModel.setState(state);

        chatSideBarViewModel.firePropertyChanged();
    }
}
