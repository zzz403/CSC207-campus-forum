package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.use_case.chat.ChatSideBarInputBoundary;
import com.imperial.academia.use_case.chat.ChatSideBarInputData;
import com.imperial.academia.use_case.chat.ChatWindowInputBoundary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ChatSideBarControllerTest {

    private ChatSideBarController chatSideBarController;
    private ChatSideBarInputBoundary chatSideBarInteractor;
    private ChatWindowInputBoundary chatWindowInteractor;

    @BeforeEach
    void setUp() {
        chatSideBarInteractor = mock(ChatSideBarInputBoundary.class);
        chatWindowInteractor = mock(ChatWindowInputBoundary.class);

        chatSideBarController = new ChatSideBarController(chatSideBarInteractor, chatWindowInteractor);
    }

    @Test
    void testExecute() {
        String chatGroupName = "Test Group";
        chatSideBarController.execute(chatGroupName);

        verify(chatSideBarInteractor, times(1)).execute(any(ChatSideBarInputData.class));
    }

    @Test
    void testSelectChatGroup() {
        int chatGroupId = 1;
        chatSideBarController.selectChatGroup(chatGroupId);

        verify(chatWindowInteractor, times(1)).execute(chatGroupId);
    }
}
