package com.imperial.academia.service;

import com.imperial.academia.cache.BoardCache;
import com.imperial.academia.data_access.board.BoardDAI;
import com.imperial.academia.entity.board.Board;

import java.sql.SQLException;
import java.util.List;

public class BoardServiceImpl implements BoardService {
    private BoardCache boardCache;
    private BoardDAI boardDAO;

    public BoardServiceImpl(BoardCache boardCache, BoardDAI boardDAO) {
        this.boardCache = boardCache;
        this.boardDAO = boardDAO;
    }

    @Override
    public void insert(Board board) throws SQLException {
        boardDAO.insert(board);
        boardCache.setBoard("board:" + board.getId(), board);
    }

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

    @Override
    public List<Board> getAll() throws SQLException {
        List<Board> boards = boardCache.getBoards("boards:all");
        if (boards == null) {
            boards = boardDAO.getAll();
            boardCache.setBoards("boards:all", boards);
        }
        return boards;
    }

    @Override
    public void update(Board board) throws SQLException {
        boardDAO.update(board);
        boardCache.setBoard("board:" + board.getId(), board);
    }

    @Override
    public void delete(int id) throws SQLException {
        boardDAO.delete(id);
        boardCache.deleteBoard("board:" + id);
    }
}
