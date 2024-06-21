package com.imperial.academia.data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.imperial.academia.config.DatabaseConfig;

/**
 * The DatabaseConnection class provides a method to establish a connection
 * to the database using the configuration details specified in DatabaseConfig.
 */
public class DatabaseConnection {
    /**
     * Establishes a connection to the database.
     * 
     * @return A Connection object to the database.
     * @throws SQLException If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC driver class is not found.
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // Load the JDBC driver class
        Class.forName(DatabaseConfig.getJdbcDriver());
        // Establish and return a connection to the database
        return DriverManager.getConnection(DatabaseConfig.getDbUrlName(), DatabaseConfig.getUser(), DatabaseConfig.getPass());
    }
}
