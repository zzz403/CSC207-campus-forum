package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.use_case.chat.ChatWindowOutputBoundary;
import com.imperial.academia.use_case.chat.ChatWindowOutputData;

/**
 * The ChatWindowPresenter class implements the ChatWindowOutputBoundary interface
 * and is responsible for updating the ChatWindowViewModel with chat messages or errors.
 */
public class ChatWindowPresenter implements ChatWindowOutputBoundary {
    private final ChatWindowViewModel chatWindowViewModel;

    /**
     * Constructs a ChatWindowPresenter with the specified view model.
     *
     * @param chatWindowViewModel The view model to be updated by this presenter.
     */
    public ChatWindowPresenter(ChatWindowViewModel chatWindowViewModel) {
        this.chatWindowViewModel = chatWindowViewModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void presentChatMessages(ChatWindowOutputData chatWindowOutputData) {
        ChatWindowState state = chatWindowViewModel.getState();
        state.setChatMessages(chatWindowOutputData.getChatMessages());
        state.setChatGroupId(chatWindowOutputData.getChatGroupId());
        chatWindowViewModel.setState(state);
        chatWindowViewModel.firePropertyChanged();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void presentError(String error) {
        ChatWindowState state = chatWindowViewModel.getState();
        state.setError(error);
        chatWindowViewModel.setState(state);
        chatWindowViewModel.firePropertyChanged();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void presentSummary(String chatWindowOutputData) {
        ChatWindowState state = chatWindowViewModel.getState();
        state.setSummary(chatWindowOutputData);
        chatWindowViewModel.setState(state);
        chatWindowViewModel.firePropertyChanged("summary");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void presentSpeechToText(String chatWindowOutputData) {
        ChatWindowState state = chatWindowViewModel.getState();
        state.setTranscription(chatWindowOutputData);
        chatWindowViewModel.setState(state);
        chatWindowViewModel.firePropertyChanged("transcription");
    }
}
