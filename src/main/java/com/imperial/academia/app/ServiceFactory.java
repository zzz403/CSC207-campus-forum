package com.imperial.academia.app;

import com.imperial.academia.cache.UserCache;
import com.imperial.academia.cache.UserCacheImpl;
import com.imperial.academia.data_access.DatabaseConnection;
import com.imperial.academia.data_access.UserDAO;
import com.imperial.academia.service.UserService;
import com.imperial.academia.service.UserServiceImpl;

import java.sql.SQLException;

/**
 * Factory class for managing and providing service instances.
 */
public class ServiceFactory {
    private static UserService userService;

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
    }

    /**
     * Returns the UserService instance.
     *
     * @return the user service
     */
    public static UserService getUserService() {
        return userService;
    }
}
