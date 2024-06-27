package com.imperial.academia.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.BufferedReader;
import java.io.IOException;

public class H2Setup {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./database/academia_imperial";
    static final String USER = "sa";
    static final String PASS = "";
    static final String FILE_PATH = "./database/example_data.sql";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        BufferedReader reader = null;

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to H2 database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            conn.setAutoCommit(false); // 开始事务

            System.out.println("Dropping existing tables if they exist...");
            // File file = new File("./database/academia_imperial.mv.db");
            

            // if (file.exists()) {
            //     // 尝试删除文件
            //     if (file.delete()) {
            //         System.out.println("文件已成功删除");
            //     } else {
            //         System.out.println("文件删除失败");
            //     }
            // } else {
            //     System.out.println("文件不存在");
            // }
            // Thread.sleep(2000);
            System.out.println("Creating tables in the H2 database...");

            String createUsersTable = "CREATE TABLE users (" +
                "user_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "username VARCHAR(50) NOT NULL UNIQUE, " +
                "password VARCHAR(255) NOT NULL, " +
                "email VARCHAR(100) NOT NULL UNIQUE, " +
                "role VARCHAR(20) NOT NULL, " +
                "registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "avatar_url VARCHAR(255), " +
                "token VARCHAR(255) DEFAULT NULL, " +
                "token_expiry BIGINT DEFAULT NULL, " +
                "last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
                ")";
            stmt.executeUpdate(createUsersTable);

            String createBoardsTable = "CREATE TABLE boards (" +
                "board_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "description TEXT, " +
                "creator_id INT, " +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (creator_id) REFERENCES users(user_id)" +
                ")";
            stmt.executeUpdate(createBoardsTable);

            String createPostsTable = "CREATE TABLE posts (" +
                "post_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "title VARCHAR(255) NOT NULL, " +
                "content TEXT NOT NULL, " +
                "author_id INT NOT NULL, " +
                "board_id INT NOT NULL, " +
                "creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (author_id) REFERENCES users(user_id), " +
                "FOREIGN KEY (board_id) REFERENCES boards(board_id)" +
                ")";
            stmt.executeUpdate(createPostsTable);

            String createCommentsTable = "CREATE TABLE comments (" +
                "comment_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "content TEXT NOT NULL, " +
                "author_id INT NOT NULL, " +
                "post_id INT NOT NULL, " +
                "parent_comment_id INT DEFAULT NULL, " +
                "creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (author_id) REFERENCES users(user_id), " +
                "FOREIGN KEY (post_id) REFERENCES posts(post_id), " +
                "FOREIGN KEY (parent_comment_id) REFERENCES comments(comment_id)" +
                ")";
            stmt.executeUpdate(createCommentsTable);

            String createChatGroupsTable = "CREATE TABLE chat_groups (" +
                "group_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "group_name VARCHAR(100) NOT NULL, " +
                "creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
                ")";
            stmt.executeUpdate(createChatGroupsTable);

            String createChatMessagesTable = "CREATE TABLE chat_messages (" +
                "message_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "sender_id INT NOT NULL, " +
                "recipient_id INT NOT NULL, " +
                "group_id INT DEFAULT NULL, " +
                "content_type VARCHAR(20) DEFAULT 'text'," +
                "content TEXT NOT NULL, " +
                "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (sender_id) REFERENCES users(user_id), " +
                "FOREIGN KEY (recipient_id) REFERENCES users(user_id), " +
                "FOREIGN KEY (group_id) REFERENCES chat_groups(group_id)" +
                ")";
            stmt.executeUpdate(createChatMessagesTable);

            String createGroupMembersTable = "CREATE TABLE group_members (" +
                "group_id INT NOT NULL, " +
                "user_id INT NOT NULL, " +
                "role VARCHAR(20) DEFAULT 'member', " +
                "joined_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, " +
                "PRIMARY KEY (group_id, user_id), " +
                "FOREIGN KEY (group_id) REFERENCES chat_groups(group_id), " +
                "FOREIGN KEY (user_id) REFERENCES users(user_id)" +
                ")";
            stmt.executeUpdate(createGroupMembersTable);

            String createPostLikesTable = "CREATE TABLE post_likes (" +
                "user_id INT, " +
                "post_id INT, " +
                "liked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "PRIMARY KEY (user_id, post_id), " +
                "FOREIGN KEY (user_id) REFERENCES users(user_id), " +
                "FOREIGN KEY (post_id) REFERENCES posts(post_id)" +
                ")";
            stmt.executeUpdate(createPostLikesTable);

            String createCommentLikesTable = "CREATE TABLE comment_likes (" +
                "user_id INT, " +
                "comment_id INT, " +
                "liked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "PRIMARY KEY (user_id, comment_id), " +
                "FOREIGN KEY (user_id) REFERENCES users(user_id), " +
                "FOREIGN KEY (comment_id) REFERENCES comments(comment_id)" +
                ")";
            stmt.executeUpdate(createCommentLikesTable);

            String createUserFollowsTable = "CREATE TABLE user_follows (" +
                "follower_id INT, " +
                "followee_id INT, " +
                "followed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "PRIMARY KEY (follower_id, followee_id), " +
                "FOREIGN KEY (follower_id) REFERENCES users(user_id), " +
                "FOREIGN KEY (followee_id) REFERENCES users(user_id)" +
                ")";
            stmt.executeUpdate(createUserFollowsTable);

            String createFilesTable = "CREATE TABLE files (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "uploader_id INT, " +
                "post_id INT, " +
                "comment_id INT, " +
                "file_url VARCHAR(255), " +
                "uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (uploader_id) REFERENCES users(user_id), " +
                "FOREIGN KEY (post_id) REFERENCES posts(post_id), " +
                "FOREIGN KEY (comment_id) REFERENCES comments(comment_id)" +
                ")";
            stmt.executeUpdate(createFilesTable);

            String createNotificationsTable = "CREATE TABLE notifications (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "user_id INT, " +
                "type VARCHAR(50), " +
                "reference_id INT, " +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "read_at TIMESTAMP, " +
                "last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (user_id) REFERENCES users(user_id)" +
                ")";
            stmt.executeUpdate(createNotificationsTable);

            String createRolesTable = "CREATE TABLE roles (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "description VARCHAR(255)" +
                ")";
            stmt.executeUpdate(createRolesTable);

            String createUserRolesTable = "CREATE TABLE user_roles (" +
                "user_id INT, " +
                "role_id INT, " +
                "assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "PRIMARY KEY (user_id, role_id), " +
                "FOREIGN KEY (user_id) REFERENCES users(user_id), " +
                "FOREIGN KEY (role_id) REFERENCES roles(id)" +
                ")";
            stmt.executeUpdate(createUserRolesTable);

            String createPermissionsTable = "CREATE TABLE permissions (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "description VARCHAR(255)" +
                ")";
            stmt.executeUpdate(createPermissionsTable);

            String createRolePermissionsTable = "CREATE TABLE role_permissions (" +
                "role_id INT, " +
                "permission_id INT, " +
                "PRIMARY KEY (role_id, permission_id), " +
                "FOREIGN KEY (role_id) REFERENCES roles(id), " +
                "FOREIGN KEY (permission_id) REFERENCES permissions(id)" +
                ")";
            stmt.executeUpdate(createRolePermissionsTable);

            String createUserStatisticsTable = "CREATE TABLE user_statistics (" +
                "user_id INT PRIMARY KEY, " +
                "login_count INT DEFAULT 0, " +
                "post_count INT DEFAULT 0, " +
                "comment_count INT DEFAULT 0, " +
                "FOREIGN KEY (user_id) REFERENCES users(user_id)" +
                ")";
            stmt.executeUpdate(createUserStatisticsTable);

            String insertRoles = "INSERT INTO roles (name, description) VALUES " +
                "('User', 'Regular user with limited permissions'), " +
                "('Moderator', 'User with moderation permissions'), " +
                "('Admin', 'Administrator with full permissions')";
            stmt.executeUpdate(insertRoles);

            String insertPermissions = "INSERT INTO permissions (name, description) VALUES " +
                "('post:create', 'Permission to create a post'), " +
                "('post:read', 'Permission to read posts'), " +
                "('post:delete', 'Permission to delete posts'), " +
                "('comment:create', 'Permission to create a comment'), " +
                "('comment:read', 'Permission to read comments'), " +
                "('comment:delete', 'Permission to delete comments'), " +
                "('like:create', 'Permission to like a post or comment'), " +
                "('user:manage', 'Permission to manage users')";
            stmt.executeUpdate(insertPermissions);

            String insertRolePermissions = "INSERT INTO role_permissions (role_id, permission_id) VALUES " +
                "(1, 1), (1, 2), (1, 4), (1, 5), (1, 7), " +
                "(2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6), (2, 7), " +
                "(3, 1), (3, 2), (3, 3), (3, 4), (3, 5), (3, 6), (3, 7), (3, 8)";
            stmt.executeUpdate(insertRolePermissions);

            System.out.println("Reading SQL data from file...");
            
            String sql = "RUNSCRIPT FROM '" + FILE_PATH + "'";
            stmt.execute(sql);

            System.out.println("SQL file imported successfully");
            

            conn.commit(); // 提交事务
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
