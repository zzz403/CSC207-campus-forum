package com.imperial.academia.service;

import com.imperial.academia.cache.BoardCache;
import com.imperial.academia.data_access.BoardDAI;
import com.imperial.academia.entity.board.Board;

import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of the BoardService interface.
 * Uses caching to reduce database access.
 */
public class BoardServiceImpl implements BoardService {
    private BoardCache boardCache;
    private BoardDAI boardDAO;

    /**
     * Constructs a new BoardServiceImpl with the specified cache and DAO.
     *
     * @param boardCache the cache to use
     * @param boardDAO the DAO to use
     */
    public BoardServiceImpl(BoardCache boardCache, BoardDAI boardDAO) {
        this.boardCache = boardCache;
        this.boardDAO = boardDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(Board board) throws SQLException {
        boardDAO.insert(board);
        boardCache.setBoard("board:" + board.getId(), board);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Board get(int id) throws SQLException {
        Board board = boardCache.getBoard("board:" + id);
        if (board == null) {
            board = boardDAO.get(id);
            if (board != null) {
                boardCache.setBoard("board:" + id, board);
            }
        }
        return board;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Board> getAll() throws SQLException {
        List<Board> boards = boardCache.getBoards("boards:all");
        if (boards == null) {
            boards = boardDAO.getAll();
            boardCache.setBoards("boards:all", boards);
        }
        return boards;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Board board) throws SQLException {
        boardDAO.update(board);
        boardCache.setBoard("board:" + board.getId(), board);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int id) throws SQLException {
        boardDAO.delete(id);
        boardCache.deleteBoard("board:" + id);
    }
}
