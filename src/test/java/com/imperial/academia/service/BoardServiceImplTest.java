package com.imperial.academia.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.imperial.academia.cache.BoardCache;
import com.imperial.academia.data_access.BoardDAI;
import com.imperial.academia.entity.board.Board;
import org.junit.*;
import org.mockito.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class BoardServiceImplTest {
    @Mock
    private BoardCache boardCache;
    @Mock
    private BoardDAI boardDAO;

    @InjectMocks
    private BoardServiceImpl boardService;

    private AutoCloseable closeable;

    private Timestamp now;

    @Before
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        now = new Timestamp(System.currentTimeMillis());
    }

    @After
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void testInsert() throws SQLException {
        Board board = new Board(1, "Board 1", "Description 1", 1, now, now);
        boardService.insert(board);
        verify(boardDAO, times(1)).insert(board);
        verify(boardCache, times(1)).setBoard("board:1", board);
    }

    @Test
    public void testGet_Cached() throws SQLException {
        Board board = new Board(1, "Board 1", "Description 1", 1, now, now);
        when(boardCache.getBoard("board:1")).thenReturn(board);

        Board result = boardService.get(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(boardCache, times(1)).getBoard("board:1");
        verifyNoMoreInteractions(boardDAO);
    }

    @Test
    public void testGet_NotCached() throws SQLException {
        when(boardCache.getBoard("board:1")).thenReturn(null);
        Board board = new Board(1, "Board 1", "Description 1", 1, now, now);
        when(boardDAO.get(1)).thenReturn(board);

        Board result = boardService.get(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(boardCache, times(1)).getBoard("board:1");
        verify(boardDAO, times(1)).get(1);
        verify(boardCache, times(1)).setBoard("board:1", board);
    }

    @Test
    public void testGetAllBoards_Cached() throws SQLException {
        Board board1 = new Board(1, "Board 1", "Description 1", 1, now, now);
        Board board2 = new Board(2, "Board 2", "Description 2", 1, now, now);
        List<Board> boards = Arrays.asList(board1, board2);
        when(boardCache.getBoards("boards:all")).thenReturn(boards);

        List<Board> result = boardService.getAllBoards();
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(boardCache, times(1)).getBoards("boards:all");
        verifyNoMoreInteractions(boardDAO);
    }

    @Test
    public void testGetAllBoards_NotCached() throws SQLException {
        when(boardCache.getBoards("boards:all")).thenReturn(null);
        Board board1 = new Board(1, "Board 1", "Description 1", 1, now, now);
        Board board2 = new Board(2, "Board 2", "Description 2", 1, now, now);
        List<Board> boards = Arrays.asList(board1, board2);
        when(boardDAO.getAll()).thenReturn(boards);

        List<Board> result = boardService.getAllBoards();
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(boardCache, times(1)).getBoards("boards:all");
        verify(boardDAO, times(1)).getAll();
        verify(boardCache, times(1)).setBoards("boards:all", boards);
    }

    @Test
    public void testGetBoardIdByName() throws SQLException {
        Board board1 = new Board(1, "Board 1", "Description 1", 1, now, now);
        Board board2 = new Board(2, "Board 2", "Description 2", 1, now, now);
        List<Board> boards = Arrays.asList(board1, board2);
        when(boardCache.getBoards("boards:all")).thenReturn(boards);

        int result = boardService.getBoardIdByName("Board 1");
        assertEquals(1, result);
    }

    @Test
    public void testGetAllBoardsName() throws SQLException {
        Board board1 = new Board(1, "Board 1", "Description 1", 1, now, now);
        Board board2 = new Board(2, "Board 2", "Description 2", 1, now, now);
        List<Board> boards = Arrays.asList(board1, board2);
        when(boardCache.getBoards("boards:all")).thenReturn(boards);

        List<String> result = boardService.getAllBoardsName();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("Board 1"));
        assertTrue(result.contains("Board 2"));
    }

    @Test
    public void testUpdate() throws SQLException {
        Board board = new Board(1, "Board 1", "Description 1", 1, now, now);
        boardService.update(board);
        verify(boardDAO, times(1)).update(board);
        verify(boardCache, times(1)).setBoard("board:1", board);
    }

    @Test
    public void testDelete() throws SQLException {
        boardService.delete(1);
        verify(boardDAO, times(1)).delete(1);
        verify(boardCache, times(1)).deleteBoard("board:1");
    }
}
