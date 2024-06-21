package com.imperial.academia.service;

import com.imperial.academia.entity.board.Board;
import java.sql.SQLException;
import java.util.List;

public interface BoardService {
    void insert(Board board) throws SQLException;

    Board get(int id) throws SQLException;

    List<Board> getAll() throws SQLException;

    void update(Board board) throws SQLException;

    void delete(int id) throws SQLException;
}
