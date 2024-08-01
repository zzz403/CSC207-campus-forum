package com.imperial.academia.entity.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;

    @BeforeEach
    public void init(){
        board = new Board(
                99,
                "name",
                "description",
                999,
                new Timestamp(1),
                new Timestamp(2)
        );
    }

    @Test
    void setName() {
        board.setName("name12");
        assertEquals("name12", board.getName());
    }

    @Test
    void setDescription() {
        board.setDescription("qwe");
        assertEquals("qwe", board.getDescription());
    }

    @Test
    void setCreatorId() {
        board.setCreatorId(777);
        assertEquals(777,board.getCreatorId());
    }

    @Test
    void setCreationDate() {
        board.setCreationDate(new Timestamp(777));
        assertEquals(new Timestamp(777), board.getCreationDate());
    }

    @Test
    void setLastModified() {
        board.setLastModified(new Timestamp(444));
        assertEquals(new Timestamp(444), board.getLastModified());
    }
}