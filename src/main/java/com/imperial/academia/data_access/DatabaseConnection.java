package com.imperial.academia.data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.imperial.academia.config.DatabaseConfig;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DatabaseConfig.getJdbcDriver());
        return DriverManager.getConnection(DatabaseConfig.getDbUrlName(), DatabaseConfig.getUser(), DatabaseConfig.getPass());
    }
}
