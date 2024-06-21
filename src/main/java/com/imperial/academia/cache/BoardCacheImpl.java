package com.imperial.academia.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.imperial.academia.entity.board.Board;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BoardCacheImpl implements BoardCache {
    private Cache<String, Board> boardCache;
    private Cache<String, List<Board>> boardsCache;

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

    @Override
    public void setBoard(String key, Board board) {
        boardCache.put(key, board);
    }

    @Override
    public Board getBoard(String key) {
        return boardCache.getIfPresent(key);
    }

    @Override
    public void deleteBoard(String key) {
        boardCache.invalidate(key);
    }

    @Override
    public boolean existsBoard(String key) {
        return boardCache.getIfPresent(key) != null;
    }

    @Override
    public void setBoards(String key, List<Board> boards) {
        boardsCache.put(key, boards);
    }

    @Override
    public List<Board> getBoards(String key) {
        return boardsCache.getIfPresent(key);
    }

    @Override
    public void deleteBoards(String key) {
        boardsCache.invalidate(key);
    }

    @Override
    public boolean existsBoards(String key) {
        return boardsCache.getIfPresent(key) != null;
    }
}
