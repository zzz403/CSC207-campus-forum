package com.imperial.academia.cache;

import com.imperial.academia.entity.board.Board;
import java.util.List;

public interface BoardCache {
    void setBoard(String key, Board board);
    Board getBoard(String key);
    void deleteBoard(String key);
    boolean existsBoard(String key);

    void setBoards(String key, List<Board> boards);
    List<Board> getBoards(String key);
    void deleteBoards(String key);
    boolean existsBoards(String key);
}
