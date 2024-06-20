package com.imperial.academia.database;

import com.imperial.academia.config.DatabaseConfig;
import com.imperial.academia.data_access.board.BoardDAO;
import com.imperial.academia.data_access.user.UserDAO;
import com.imperial.academia.entity.*;
import com.imperial.academia.entity.user.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class DatabaseTest {

    public static void main(String[] args) {
        Connection conn = null;

        try {
            Class.forName(DatabaseConfig.getJdbcDriver());

            conn = DriverManager.getConnection(DatabaseConfig.getDbUrlName(), DatabaseConfig.getUser(), DatabaseConfig.getPass());

            // Test UserDAO
            testUserDAO(conn);

            // Test BoardDAO
            testBoardDAO(conn);

        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private static void testUserDAO(Connection conn) {
        try {
            UserDAO userDAO = new UserDAO(conn);
            
            User existingUser = userDAO.getByUsername("testuser");
            if (existingUser != null) {
                userDAO.delete(existingUser.getId());
            }

            User user = new User(0, "testuser", "testpass", "testuser@example.com", "user", new Timestamp(System.currentTimeMillis()));
            userDAO.insert(user);
            System.out.println("Inserted User: " + user);

            User retrievedUser = userDAO.getByUsername("testuser");
            user.setId(retrievedUser.getId());

            System.out.println("Retrieved User: " + retrievedUser);

            List<User> users = userDAO.getAll();
            System.out.println("All Users: " + users);

            user.setUsername("updateduser");
            userDAO.update(user);
            System.out.println("Updated User: " + userDAO.get(user.getId()));

            userDAO.delete(user.getId());
            System.out.println("Deleted User. Attempting to retrieve: " + userDAO.get(user.getId()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testBoardDAO(Connection conn) {
        try {
            BoardDAO boardDAO = new BoardDAO(conn);
            Board board = new Board(0, "Test Board", "This is a test board");
            boardDAO.insert(board);
            System.out.println("Inserted Board: " + board);

            Board retrievedBoard = boardDAO.getByName("Test Board");
            board.setId(retrievedBoard.getId());

            System.out.println("Retrieved Board: " + retrievedBoard);

            List<Board> boards = boardDAO.getAll();
            System.out.println("All Boards: " + boards);

            board.setName("Updated Board");
            boardDAO.update(board);
            System.out.println("Updated Board: " + boardDAO.get(board.getId()));

            boardDAO.delete(board.getId());
            System.out.println("Deleted Board. Attempting to retrieve: " + boardDAO.get(board.getId()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
