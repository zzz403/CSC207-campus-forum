package com.imperial.academia.cache;

import com.imperial.academia.entity.board.Board;
import java.util.List;

/**
 * Interface for caching board entities.
 */
public interface BoardCache {
    /**
     * Caches a single board.
     *
     * @param key the key for the board
     * @param board the board to cache
     */
    void setBoard(String key, Board board);

    /**
     * Retrieves a single cached board.
     *
     * @param key the key for the board
     * @return the cached board, or null if not found
     */
    Board getBoard(String key);

    /**
     * Deletes a single cached board.
     *
     * @param key the key for the board to delete
     */
    void deleteBoard(String key);

    /**
     * Checks if a single board is cached.
     *
     * @param key the key for the board
     * @return true if the board is cached, false otherwise
     */
    boolean existsBoard(String key);

    /**
     * Caches a list of boards.
     *
     * @param key the key for the list of boards
     * @param boards the list of boards to cache
     */
    void setBoards(String key, List<Board> boards);

    /**
     * Retrieves a list of cached boards.
     *
     * @param key the key for the list of boards
     * @return the cached list of boards, or null if not found
     */
    List<Board> getBoards(String key);

    /**
     * Deletes a list of cached boards.
     *
     * @param key the key for the list of boards to delete
     */
    void deleteBoards(String key);

    /**
     * Checks if a list of boards is cached.
     *
     * @param key the key for the list of boards
     * @return true if the list of boards is cached, false otherwise
     */
    boolean existsBoards(String key);
}
