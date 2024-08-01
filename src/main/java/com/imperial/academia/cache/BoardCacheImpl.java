package com.imperial.academia.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.imperial.academia.entity.board.Board;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of the BoardCache interface using Guava Cache.
 */
public class BoardCacheImpl implements BoardCache {
    private Cache<String, Board> boardCache;
    private Cache<String, List<Board>> boardsCache;

    /**
     * Constructs a new BoardCacheImpl with specific cache configurations.
     */
    public BoardCacheImpl() {
        boardCache = CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build();

        boardsCache = CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();
    }

    /**
     * Constructs a new BoardCacheImpl with the specified caches.
     * @param boardCache
     * @param boardsCache
     */
    public BoardCacheImpl(Cache<String, Board> boardCache, Cache<String, List<Board>> boardsCache) {
        this.boardCache = boardCache;
        this.boardsCache = boardsCache;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBoard(String key, Board board) {
        boardCache.put(key, board);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Board getBoard(String key) {
        return boardCache.getIfPresent(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteBoard(String key) {
        boardCache.invalidate(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsBoard(String key) {
        return boardCache.getIfPresent(key) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBoards(String key, List<Board> boards) {
        boardsCache.put(key, boards);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Board> getBoards(String key) {
        return boardsCache.getIfPresent(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteBoards(String key) {
        boardsCache.invalidate(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsBoards(String key) {
        return boardsCache.getIfPresent(key) != null;
    }
}
