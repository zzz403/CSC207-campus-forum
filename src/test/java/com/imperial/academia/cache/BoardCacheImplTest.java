package com.imperial.academia.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.common.cache.Cache;
import com.imperial.academia.entity.board.Board;

class BoardCacheImplTest {

    private BoardCacheImpl boardCacheImpl;
    private Cache<String, Board> mockBoardCache;
    private Cache<String, List<Board>> mockBoardsCache;

    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() {
        mockBoardCache = Mockito.mock(Cache.class);
        mockBoardsCache = Mockito.mock(Cache.class);

        boardCacheImpl = new BoardCacheImpl(mockBoardCache, mockBoardsCache);
    }

    @Test
    void testDefaultConstructor() {
        boardCacheImpl = new BoardCacheImpl();
        assertNotNull(boardCacheImpl);
    }

    @Test
    void setBoard_shouldPutBoardInCache() {
        Board board = new Board(1, "Test Board", "Description", 1, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        boardCacheImpl.setBoard("testKey", board);

        verify(mockBoardCache, times(1)).put("testKey", board);
    }

    @Test
    void getBoard_shouldReturnBoardFromCache() {
        Board board = new Board(1, "Test Board", "Description", 1, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        when(mockBoardCache.getIfPresent("testKey")).thenReturn(board);

        Board result = boardCacheImpl.getBoard("testKey");

        assertEquals(board, result);
        verify(mockBoardCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void deleteBoard_shouldInvalidateBoardInCache() {
        boardCacheImpl.deleteBoard("testKey");

        verify(mockBoardCache, times(1)).invalidate("testKey");
    }

    @Test
    void existsBoard_shouldReturnTrueIfBoardExistsInCache() {
        Board board = new Board(1, "Test Board", "Description", 1, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        when(mockBoardCache.getIfPresent("testKey")).thenReturn(board);

        boolean result = boardCacheImpl.existsBoard("testKey");

        assertTrue(result);
        verify(mockBoardCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void existsBoard_shouldReturnFalseIfBoardDoesNotExistInCache() {
        when(mockBoardCache.getIfPresent("testKey")).thenReturn(null);

        boolean result = boardCacheImpl.existsBoard("testKey");

        assertFalse(result);
        verify(mockBoardCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void setBoards_shouldPutBoardsInCache() {
        List<Board> boards = new ArrayList<>();
        boardCacheImpl.setBoards("testKey", boards);

        verify(mockBoardsCache, times(1)).put("testKey", boards);
    }

    @Test
    void getBoards_shouldReturnBoardsFromCache() {
        List<Board> boards = new ArrayList<>();
        when(mockBoardsCache.getIfPresent("testKey")).thenReturn(boards);

        List<Board> result = boardCacheImpl.getBoards("testKey");

        assertEquals(boards, result);
        verify(mockBoardsCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void deleteBoards_shouldInvalidateBoardsInCache() {
        boardCacheImpl.deleteBoards("testKey");

        verify(mockBoardsCache, times(1)).invalidate("testKey");
    }

    @Test
    void existsBoards_shouldReturnTrueIfBoardsExistInCache() {
        List<Board> boards = new ArrayList<>();
        when(mockBoardsCache.getIfPresent("testKey")).thenReturn(boards);

        boolean result = boardCacheImpl.existsBoards("testKey");

        assertTrue(result);
        verify(mockBoardsCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void existsBoards_shouldReturnFalseIfBoardsDoNotExistInCache() {
        when(mockBoardsCache.getIfPresent("testKey")).thenReturn(null);

        boolean result = boardCacheImpl.existsBoards("testKey");

        assertFalse(result);
        verify(mockBoardsCache, times(1)).getIfPresent("testKey");
    }
}
