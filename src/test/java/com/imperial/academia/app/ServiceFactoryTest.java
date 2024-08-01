// ServiceFactoryTest.java
package com.imperial.academia.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.SQLException;

import com.imperial.academia.cache.*;
import com.imperial.academia.data_access.*;
import com.imperial.academia.service.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class ServiceFactoryTest {

    private UserDAO userDAOMock;
    private ChatGroupDAO chatGroupDAOMock;
    private GroupMemberDAO groupMemberDAOMock;
    private ChatMessageDAO chatMessageDAOMock;
    private BoardDAO boardDAOMock;
    private PostDAO postDAOMock;

    private Connection connectionMock;

    @BeforeEach
    public void setUp() throws SQLException, ClassNotFoundException {
        // Mocking the connection and DAOs
        connectionMock = mock(Connection.class);

        userDAOMock = mock(UserDAO.class);
        chatGroupDAOMock = mock(ChatGroupDAO.class);
        groupMemberDAOMock = mock(GroupMemberDAO.class);
        chatMessageDAOMock = mock(ChatMessageDAO.class);
        boardDAOMock = mock(BoardDAO.class);
        postDAOMock = mock(PostDAO.class);

        try (MockedStatic<DatabaseConnection> mockedDatabaseConnection = Mockito.mockStatic(DatabaseConnection.class)) {
            mockedDatabaseConnection.when(DatabaseConnection::getConnection).thenReturn(connectionMock);

            // Mocking the constructors of the DAOs to return our mocks
            try (MockedStatic<UserDAO> mockedUserDAO = Mockito.mockStatic(UserDAO.class)) {
                mockedUserDAO.when(() -> new UserDAO(connectionMock)).thenReturn(userDAOMock);

                try (MockedStatic<ChatGroupDAO> mockedChatGroupDAO = Mockito.mockStatic(ChatGroupDAO.class)) {
                    mockedChatGroupDAO.when(() -> new ChatGroupDAO(connectionMock)).thenReturn(chatGroupDAOMock);

                    try (MockedStatic<GroupMemberDAO> mockedGroupMemberDAO = Mockito.mockStatic(GroupMemberDAO.class)) {
                        mockedGroupMemberDAO.when(() -> new GroupMemberDAO(connectionMock)).thenReturn(groupMemberDAOMock);

                        try (MockedStatic<ChatMessageDAO> mockedChatMessageDAO = Mockito.mockStatic(ChatMessageDAO.class)) {
                            mockedChatMessageDAO.when(() -> new ChatMessageDAO(connectionMock)).thenReturn(chatMessageDAOMock);

                            try (MockedStatic<BoardDAO> mockedBoardDAO = Mockito.mockStatic(BoardDAO.class)) {
                                mockedBoardDAO.when(() -> new BoardDAO(connectionMock)).thenReturn(boardDAOMock);

                                try (MockedStatic<PostDAO> mockedPostDAO = Mockito.mockStatic(PostDAO.class)) {
                                    mockedPostDAO.when(() -> new PostDAO(connectionMock)).thenReturn(postDAOMock);

                                    // Initialize the ServiceFactory
                                    ServiceFactory.initialize();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    public void testGetUserService() {
        UserService userService = ServiceFactory.getUserService();
        assertNotNull(userService);
    }

    @Test
    public void testGetChatGroupService() {
        ChatGroupService chatGroupService = ServiceFactory.getChatGroupService();
        assertNotNull(chatGroupService);
    }

    @Test
    public void testGetChatMessageService() {
        ChatMessageService chatMessageService = ServiceFactory.getChatMessageService();
        assertNotNull(chatMessageService);
    }

    @Test
    public void testGetBoardService() {
        BoardService boardService = ServiceFactory.getBoardService();
        assertNotNull(boardService);
    }

    @Test
    public void testGetPostService() {
        PostService postService = ServiceFactory.getPostService();
        assertNotNull(postService);
    }

    @Test
    public void testGetAudioService() {
        AudioService audioService = ServiceFactory.getAudioService();
        assertNotNull(audioService);
    }

    @Test
    public void testGetMapService() {
        MapService mapService = ServiceFactory.getMapService();
        assertNotNull(mapService);
    }

    @Test
    public void testGetFileService() {
        FileService fileService = ServiceFactory.getFileService();
        assertNotNull(fileService);
    }
}
