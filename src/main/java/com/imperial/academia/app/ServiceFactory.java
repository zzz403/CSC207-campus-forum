package com.imperial.academia.app;

import java.sql.SQLException;

import com.imperial.academia.cache.BoardCache;
import com.imperial.academia.cache.BoardCacheImpl;
import com.imperial.academia.cache.ChatGroupCache;
import com.imperial.academia.cache.ChatGroupCacheImpl;
import com.imperial.academia.cache.ChatMessageCache;
import com.imperial.academia.cache.ChatMessageCacheImpl;
import com.imperial.academia.cache.CommentCache;
import com.imperial.academia.cache.CommentCacheImpl;
import com.imperial.academia.cache.GroupMemberCache;
import com.imperial.academia.cache.GroupMemberCacheImpl;
import com.imperial.academia.cache.PostCache;
import com.imperial.academia.cache.PostCacheImpl;
import com.imperial.academia.cache.UserCache;
import com.imperial.academia.cache.UserCacheImpl;
import com.imperial.academia.data_access.BoardDAO;
import com.imperial.academia.data_access.ChatGroupDAI;
import com.imperial.academia.data_access.ChatGroupDAO;
import com.imperial.academia.data_access.ChatMessageDAO;
import com.imperial.academia.data_access.CommentDAO;
import com.imperial.academia.data_access.DatabaseConnection;
import com.imperial.academia.data_access.GroupMemberDAI;
import com.imperial.academia.data_access.GroupMemberDAO;
import com.imperial.academia.data_access.PostDAO;
import com.imperial.academia.data_access.UserDAO;
import com.imperial.academia.service.AudioService;
import com.imperial.academia.service.AudioServiceImpl;
import com.imperial.academia.service.BoardService;
import com.imperial.academia.service.BoardServiceImpl;
import com.imperial.academia.service.ChatGroupService;
import com.imperial.academia.service.ChatGroupServiceImpl;
import com.imperial.academia.service.ChatMessageService;
import com.imperial.academia.service.ChatMessageServiceImpl;
import com.imperial.academia.service.CommentService;
import com.imperial.academia.service.CommentServiceImpl;
import com.imperial.academia.service.FileService;
import com.imperial.academia.service.FileServiceImpl;
import com.imperial.academia.service.MapService;
import com.imperial.academia.service.MapServiceImpl;
import com.imperial.academia.service.PostService;
import com.imperial.academia.service.PostServiceImpl;
import com.imperial.academia.service.UserService;
import com.imperial.academia.service.UserServiceImpl;

/**
 * Factory class for managing and providing service instances.
 */
public class ServiceFactory {
    
    private static AudioService audioService;
    private static MapService mapService;
    private static FileService fileService;
    
    private static UserService userService;
    private static ChatGroupService chatGroupService;
    private static ChatMessageService chatMessageService;
    private static BoardService boardService;
    private static PostService postService;

    private static CommentService commentService;

    
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
        ChatGroupDAI chatGroupDAO = new ChatGroupDAO(DatabaseConnection.getConnection());
        GroupMemberCache groupMemberCache = new GroupMemberCacheImpl();
        GroupMemberDAI groupMemberDAO = new GroupMemberDAO(DatabaseConnection.getConnection());
        chatGroupService = new ChatGroupServiceImpl(chatGroupCache, chatGroupDAO,groupMemberCache,groupMemberDAO);
        
        ChatMessageCache chatMessageCache = new ChatMessageCacheImpl();
        ChatMessageDAO chatMessageDAO = new ChatMessageDAO(DatabaseConnection.getConnection());
        chatMessageService = new ChatMessageServiceImpl(chatMessageDAO, userDAO, chatMessageCache, userCache);
        
        BoardCache boardCache = new BoardCacheImpl();
        BoardDAO boardDAO = new BoardDAO(DatabaseConnection.getConnection());
        boardService = new BoardServiceImpl(boardCache, boardDAO);

        PostCache postCache = new PostCacheImpl();
        PostDAO postDAO = new PostDAO(DatabaseConnection.getConnection());
        postService = new PostServiceImpl(postCache, postDAO);

        CommentCache commentCache = new CommentCacheImpl();
        CommentDAO commentDAO = new CommentDAO(DatabaseConnection.getConnection());
        commentService = new CommentServiceImpl(commentCache, commentDAO);

        audioService = new AudioServiceImpl();

        mapService = new MapServiceImpl();

        fileService = new FileServiceImpl();
    }

    /**
     * Return the BoardService instance
     * 
     * @return BoardService
     */
    public static BoardService getBoardService() {
        return boardService;
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

    /**
     * Return the PostService instance
     * 
     * @return PostService
     */
    public static PostService getPostService() {
        return postService;
    }

    /**
     * Returns the MapService instance.
     *
     * @return the map service
     */
    public static MapService getMapService() {
        return mapService;
    }

    /**
     * Returns the FileService instance.
     *
     * @return the file service
     */
    public static FileService getFileService() {
        return fileService;
    }

    public static CommentService getCommentService() {
        return commentService;
    }
}
