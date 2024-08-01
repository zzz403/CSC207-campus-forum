package com.imperial.academia.entity.comment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {
    private Comment comment;

    @BeforeEach
    public void iit(){
        comment = new Comment(1, "",1,1,1,
                new Timestamp(1),new Timestamp(1));
    }

    @Test
    void setId() {
        comment.setId(22);
        assertEquals(22, comment.getId());
    }

    @Test
    void setContent() {
        comment.setContent("ss");
        assertEquals("ss", comment.getContent());
    }

    @Test
    void setAuthorId() {
        comment.setAuthorId(333);
        assertEquals(333, comment.getAuthorId());
    }

    @Test
    void setPostId() {
        comment.setPostId(888);
        assertEquals(888, comment.getPostId());
    }

    @Test
    void setParentCommentId() {
        comment.setParentCommentId(234);
        assertEquals(234,comment.getParentCommentId());
    }

    @Test
    void setCreationDate() {
        comment.setCreationDate(new Timestamp(8787));
        assertEquals(new Timestamp(8787),comment.getCreationDate());
    }

    @Test
    void setLastModified() {
        comment.setLastModified(new Timestamp(999));
        assertEquals(new Timestamp(999), comment.getLastModified());
    }
}