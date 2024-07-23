package com.imperial.academia.use_case.chat;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.service.ChatGroupService;
import com.imperial.academia.session.SessionManager;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

public class ChatCoordinatorInteractorTest {

    private ChatCoordinatorInteractor chatCoordinatorInteractor;
    private ChatSideBarInputBoundary mockChatSidebarInteractor;
    private ChatWindowInputBoundary mockChatWindowInteractor;
    private ChatGroupService mockChatGroupService;
    private ChangeViewInputBoundary mockChangeViewInteractor;
    private MockedStatic<UsecaseFactory> mockedUsecaseFactory;
    private MockedStatic<ServiceFactory> mockedServiceFactory;
    private MockedStatic<SessionManager> mockedSessionManager;

    @BeforeEach
    void setUp() {
        mockChatSidebarInteractor = mock(ChatSideBarInputBoundary.class);
        mockChatWindowInteractor = mock(ChatWindowInputBoundary.class);
        mockChatGroupService = mock(ChatGroupService.class);
        mockChangeViewInteractor = mock(ChangeViewInputBoundary.class);

        mockedUsecaseFactory = mockStatic(UsecaseFactory.class);
        mockedServiceFactory = mockStatic(ServiceFactory.class);
        mockedSessionManager = mockStatic(SessionManager.class);

        when(UsecaseFactory.getChatSideBarInteractor()).thenReturn(mockChatSidebarInteractor);
        when(UsecaseFactory.getChatWindowInteractor()).thenReturn(mockChatWindowInteractor);
        when(UsecaseFactory.getChangeViewInteractor()).thenReturn(mockChangeViewInteractor);
        when(ServiceFactory.getChatGroupService()).thenReturn(mockChatGroupService);

        chatCoordinatorInteractor = new ChatCoordinatorInteractor();
    }

    @Test
    void testToPrivateChat() throws Exception {
        int userId = 2;
        int chatGroupId = 1;
        User mockUser = mock(User.class);

        when(mockUser.getId()).thenReturn(1);
        when(mockUser.getUsername()).thenReturn("TestUser");
        when(SessionManager.getCurrentUser()).thenReturn(mockUser);
        when(mockChatGroupService.getPrivateChatId(1, userId)).thenReturn(chatGroupId);

        chatCoordinatorInteractor.toPrivateChat(userId);

        verify(mockChatSidebarInteractor, times(1)).execute();
        verify(mockChatWindowInteractor, times(1)).sendMessage(any(ChatWindowInputData.class));
        verify(mockChatWindowInteractor, times(1)).execute(chatGroupId);
        verify(mockChangeViewInteractor, times(1)).changeView("chat");
    }
}
