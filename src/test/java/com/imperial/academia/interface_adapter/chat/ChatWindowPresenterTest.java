package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.use_case.chat.ChatWindowOutputData;
import com.imperial.academia.entity.chat_message.ChatMessageDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ChatWindowPresenterTest {

    private ChatWindowViewModel chatWindowViewModel;
    private ChatWindowPresenter chatWindowPresenter;

    @BeforeEach
    void setUp() {
        chatWindowViewModel = mock(ChatWindowViewModel.class);
        chatWindowPresenter = new ChatWindowPresenter(chatWindowViewModel);
    }

    @Test
    void testPresentChatMessages() {
        List<ChatMessageDTO> chatMessages = List.of(
                new ChatMessageDTO(1, 1, "User1", "url1", 1, "text", "Hello", true, null),
                new ChatMessageDTO(2, 2, "User2", "url2", 1, "text", "Hi", false, null)
        );
        ChatWindowOutputData outputData = new ChatWindowOutputData(chatMessages,1);
        ChatWindowState state = new ChatWindowState();
        when(chatWindowViewModel.getState()).thenReturn(state);

        chatWindowPresenter.presentChatMessages(outputData);

        assertEquals(chatMessages, state.getChatMessages());
        assertEquals(1, state.getChatGroupId());
        verify(chatWindowViewModel).setState(state);
        verify(chatWindowViewModel).firePropertyChanged();
    }

    @Test
    void testPresentError() {
        String error = "An error occurred";
        ChatWindowState state = new ChatWindowState();
        when(chatWindowViewModel.getState()).thenReturn(state);

        chatWindowPresenter.presentError(error);

        assertEquals(error, state.getError());
        verify(chatWindowViewModel).setState(state);
        verify(chatWindowViewModel).firePropertyChanged();
    }

    @Test
    void testPresentSummary() {
        String summary = "This is a summary";
        ChatWindowState state = new ChatWindowState();
        when(chatWindowViewModel.getState()).thenReturn(state);

        chatWindowPresenter.presentSummary(summary);

        assertEquals(summary, state.getSummary());
        verify(chatWindowViewModel).setState(state);
        verify(chatWindowViewModel).firePropertyChanged("summary");
    }

    @Test
    void testPresentSpeechToText() {
        String transcription = "This is a transcription";
        ChatWindowState state = new ChatWindowState();
        when(chatWindowViewModel.getState()).thenReturn(state);

        chatWindowPresenter.presentSpeechToText(transcription);

        assertEquals(transcription, state.getTranscription());
        verify(chatWindowViewModel).setState(state);
        verify(chatWindowViewModel).firePropertyChanged("transcription");
    }

    @Test
    void testPresentTranslatedText() {
        String translatedText = "This is translated text";
        ChatWindowState state = new ChatWindowState();
        when(chatWindowViewModel.getState()).thenReturn(state);

        chatWindowPresenter.presentTranslatedText(translatedText);

        assertEquals(translatedText, state.getTranscription());
        verify(chatWindowViewModel).setState(state);
        verify(chatWindowViewModel).firePropertyChanged("transcription");
    }
}
