package com.imperial.academia.database;
import com.imperial.academia.config.DatabaseConfig;
import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DatabaseSetup {
    static final String JDBC_DRIVER = DatabaseConfig.JDBC_DRIVER;  
    static final String DB_URL = DatabaseConfig.DB_URL;
    static final String DB_NAME = DatabaseConfig.DB_NAME;
    static final String USER = DatabaseConfig.USER;
    static final String PASS = DatabaseConfig.PASS;
    static final String FILE_PATH = "./src/main/java/com/imperial/academia/database/example_data.txt";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        BufferedReader reader = null;

        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database server...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            System.out.println("Creating database...");
            String sql = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
            stmt.executeUpdate(sql);

            System.out.println("Database created successfully...");

            // Connect to the created database
            conn.close();
            String dbUrlWithDb = DB_URL + DB_NAME + "?useSSL=false&serverTimezone=UTC";
            conn = DriverManager.getConnection(dbUrlWithDb, USER, PASS);
            stmt = conn.createStatement();

            System.out.println("Creating tables in the database...");
            
            String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                "user_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "username VARCHAR(50) NOT NULL UNIQUE, " +
                "password VARCHAR(255) NOT NULL, " +
                "email VARCHAR(100) NOT NULL UNIQUE, " +
                "role ENUM('user', 'admin') NOT NULL, " +
                "registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";
            stmt.executeUpdate(createUsersTable);

            String createBoardsTable = "CREATE TABLE IF NOT EXISTS boards (" +
                "board_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "description TEXT" +
                ")";
            stmt.executeUpdate(createBoardsTable);

            String createPostsTable = "CREATE TABLE IF NOT EXISTS posts (" +
                "post_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "title VARCHAR(255) NOT NULL, " +
                "content TEXT NOT NULL, " +
                "author_id INT NOT NULL, " +
                "board_id INT NOT NULL, " +
                "creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (author_id) REFERENCES users(user_id), "+
                "FOREIGN KEY (board_id) REFERENCES boards(board_id)" +
                ")";
            stmt.executeUpdate(createPostsTable);

            String createCommentsTable = "CREATE TABLE IF NOT EXISTS comments (" +
                "comment_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "content TEXT NOT NULL, " +
                "author_id INT NOT NULL, " +
                "post_id INT NOT NULL, " +
                "parent_comment_id INT DEFAULT NULL, " +
                "creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (author_id) REFERENCES users(user_id), " +
                "FOREIGN KEY (post_id) REFERENCES posts(post_id), " +
                "FOREIGN KEY (parent_comment_id) REFERENCES comments(comment_id)" +
                ")";
            stmt.executeUpdate(createCommentsTable);

            String createChatGroupsTable = "CREATE TABLE IF NOT EXISTS chat_groups (" +
                "group_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "group_name VARCHAR(100) NOT NULL, " +
                "creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";
            stmt.executeUpdate(createChatGroupsTable);

            String createChatMessagesTable = "CREATE TABLE IF NOT EXISTS chat_messages (" +
                "message_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "sender_id INT NOT NULL, " +
                "recipient_id INT NOT NULL, " +
                "group_id INT DEFAULT NULL, " +
                "content TEXT NOT NULL, " +
                "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (sender_id) REFERENCES users(user_id), " +
                "FOREIGN KEY (recipient_id) REFERENCES users(user_id), " +
                "FOREIGN KEY (group_id) REFERENCES chat_groups(group_id)" +
                ")";
            stmt.executeUpdate(createChatMessagesTable);

            String createGroupMembersTable = "CREATE TABLE IF NOT EXISTS group_members (" +
                "group_id INT NOT NULL, " +
                "user_id INT NOT NULL, " +
                "role ENUM('member', 'admin') DEFAULT 'member', " +
                "joined_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "PRIMARY KEY (group_id, user_id), " +
                "FOREIGN KEY (group_id) REFERENCES chat_groups(group_id), " +
                "FOREIGN KEY (user_id) REFERENCES users(user_id)" +
                ")";
            stmt.executeUpdate(createGroupMembersTable);
            
            // Insert data from the file
            System.out.println("Reading SQL data from file...");
            reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            StringBuilder sqlBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                sqlBuilder.append(line);
                if (line.trim().endsWith(";")) {
                    stmt.executeUpdate(sqlBuilder.toString());
                    sqlBuilder.setLength(0);
                }
            }

            System.out.println("Data inserted successfully...");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
                if (stmt != null) stmt.close();
            } catch (IOException | SQLException se2) { }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Finish!");
    }
}
