package com.imperial.academia.service;

import com.imperial.academia.entity.board.Board;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface for Board Service.
 */
public interface BoardService {
    /**
     * Inserts a new board into the database.
     *
     * @param board the board to insert
     * @throws SQLException if a database access error occurs
     */
    void insert(Board board) throws SQLException;

    /**
     * Retrieves a board by its ID.
     *
     * @param id the ID of the board to retrieve
     * @return the board with the specified ID, or null if not found
     * @throws SQLException if a database access error occurs
     */
    Board get(int id) throws SQLException;

    /**
     * Retrieves all boards from the database.
     *
     * @return a list of all boards
     * @throws SQLException if a database access error occurs
     */
    List<Board> getAllBoards() throws SQLException;

    /**
     * Retrives all boards name from the datebase.
     * 
     * @return a list of all boards name
     * @throws SQLException
     */
    List<String> getAllBoardsName() throws SQLException;

    /**
     * Updates an existing board in the database.
     *
     * @param board the board to update
     * @throws SQLException if a database access error occurs
     */
    void update(Board board) throws SQLException;

    /**
     * Deletes a board by its ID.
     *
     * @param id the ID of the board to delete
     * @throws SQLException if a database access error occurs
     */
    void delete(int id) throws SQLException;
}
