package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.use_case.chat.ChatWindowOutputBoundary;
import com.imperial.academia.use_case.chat.ChatWindowOutputData;

public class ChatWindowPresenter implements ChatWindowOutputBoundary {
    private final ChatWindowViewModel chatWindowViewModel;

    public ChatWindowPresenter(ChatWindowViewModel chatWindowViewModel) {
        this.chatWindowViewModel = chatWindowViewModel;
    }

    @Override
    public void presentChatMessages(ChatWindowOutputData chatWindowOutputData) {
        ChatWindowState state = chatWindowViewModel.getState();
        state.setChatMessages(chatWindowOutputData.getChatMessages());
        state.setChatGroupId(chatWindowOutputData.getChatGroupId());
        chatWindowViewModel.setState(state);
        chatWindowViewModel.firePropertyChanged();
    }

    @Override
    public void presentError(String error) {
        ChatWindowState state = chatWindowViewModel.getState();
        state.setError(error);
        chatWindowViewModel.setState(state);
        chatWindowViewModel.firePropertyChanged();
    }
}
