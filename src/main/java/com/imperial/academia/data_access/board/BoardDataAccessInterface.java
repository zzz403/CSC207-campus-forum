package com.imperial.academia.data_access.board;

import java.util.List;

import com.imperial.academia.entity.Board;

public interface BoardDataAccessInterface {
    void save(Board board);
    Board findById(int id);
    List<Board> findAll();
}
