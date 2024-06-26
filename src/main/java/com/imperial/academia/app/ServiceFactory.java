package com.imperial.academia.app;

import com.imperial.academia.cache.*;
import com.imperial.academia.data_access.*;
import com.imperial.academia.service.*;

import java.sql.SQLException;

/**
 * Factory class for managing and providing service instances.
 */
public class ServiceFactory {
    private static UserService userService;
    private static ChatGroupService chatGroupService;
    private static ChatMessageService chatMessageService;

    private static AudioService audioService;

    /**
     * Initializes the service factory by creating and configuring the necessary services.
     *
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the database driver class is not found
     */
    public static void initialize() throws SQLException, ClassNotFoundException {
        UserCache userCache = new UserCacheImpl();
        UserDAO userDAO = new UserDAO(DatabaseConnection.getConnection());
        userService = new UserServiceImpl(userCache, userDAO);

        ChatGroupCache chatGroupCache = new ChatGroupCacheImpl();
        ChatGroupDAO chatGroupDAO = new ChatGroupDAO(DatabaseConnection.getConnection());
        chatGroupService = new ChatGroupServiceImpl(chatGroupCache, chatGroupDAO);

        ChatMessageCache chatMessageCache = new ChatMessageCacheImpl();
        ChatMessageDAO chatMessageDAO = new ChatMessageDAO(DatabaseConnection.getConnection());
        chatMessageService = new ChatMessageServiceImpl(chatMessageDAO, userDAO, chatMessageCache, userCache);

        audioService = new AudioServiceImpl();
    }

    /**
     * Returns the UserService instance.
     *
     * @return the user service
     */
    public static UserService getUserService() {
        return userService;
    }

    /**
     * Returns the ChatGroupService instance.
     *
     * @return the chat group service
     */
    public static ChatGroupService getChatGroupService() {
        return chatGroupService;
    }

    /**
     * Returns the ChatMessageService instance.
     *
     * @return the chat message service
     */
    public static ChatMessageService getChatMessageService() {
        return chatMessageService;
    }

    /**
     * Returns the AudioService instance.
     *
     * @return the audio service
     */

    public static AudioService getAudioService() {
        return audioService;
    }
}
