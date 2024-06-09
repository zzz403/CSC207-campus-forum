package com.imperial.academia.database;

import com.imperial.academia.data_access.*;
import com.imperial.academia.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class DatabaseTest {

    public static void main(String[] args) {
        Connection conn = null;

        try {
            // Test UserDAO
            UserDAO userDAO = new UserDAO();
            User user = new User(0, "testuser", "testpass", "testuser@example.com", "user", new Timestamp(System.currentTimeMillis()));
            userDAO.insert(user);
            System.out.println("Inserted User: " + user);

            User retrievedUser = userDAO.get(user.getId());
            System.out.println("Retrieved User: " + retrievedUser);

            List<User> users = userDAO.getAll();
            System.out.println("All Users: " + users);

            user.setUsername("updateduser");
            userDAO.update(user);
            System.out.println("Updated User: " + userDAO.get(user.getId()));

            userDAO.delete(user.getId());
            System.out.println("Deleted User: " + userDAO.get(user.getId()));

            userDAO.close();

            // Test BoardDAO
            BoardDAO boardDAO = new BoardDAO();
            Board board = new Board(0, "Test Board", "This is a test board");
            boardDAO.insert(board);
            System.out.println("Inserted Board: " + board);

            Board retrievedBoard = boardDAO.get(board.getId());
            System.out.println("Retrieved Board: " + retrievedBoard);

            List<Board> boards = boardDAO.getAll();
            System.out.println("All Boards: " + boards);

            board.setName("Updated Board");
            boardDAO.update(board);
            System.out.println("Updated Board: " + boardDAO.get(board.getId()));

            boardDAO.delete(board.getId());
            System.out.println("Deleted Board: " + boardDAO.get(board.getId()));

            // Similar tests can be added for PostDAO, CommentDAO, ChatGroupDAO, ChatMessageDAO, and GroupMemberDAO

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
}
