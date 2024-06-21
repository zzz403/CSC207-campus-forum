package com.imperial.academia.app;

import com.imperial.academia.cache.UserCache;
import com.imperial.academia.cache.UserCacheImpl;
import com.imperial.academia.data_access.DatabaseConnection;
import com.imperial.academia.data_access.user.UserDAO;
import com.imperial.academia.service.UserService;
import com.imperial.academia.service.UserServiceImpl;

import java.sql.SQLException;

public class ServiceFactory {
    private static UserService userService;

    public static void initialize() throws SQLException, ClassNotFoundException {
        UserCache userCache = new UserCacheImpl();
        UserDAO userDAO = new UserDAO(DatabaseConnection.getConnection());
        userService = new UserServiceImpl(userCache, userDAO);
    }

    public static UserService getUserService() {
        return userService;
    }
}
