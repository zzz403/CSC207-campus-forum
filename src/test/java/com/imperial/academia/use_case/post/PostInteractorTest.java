package com.imperial.academia.use_case.post;

import static org.mockito.Mockito.verify;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PostInteractorTest {

    @Mock
    private PostOutputBoundary postPresenter;

    @InjectMocks
    private PostInteractor postInteractor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInitPostPageWithValidData() {
        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle("Test Title")
                .setContent("Test Content")
                .setUsername("testuser")
                .setAvatarUrl("http://example.com/avatar.jpg")
                .setDate(Timestamp.from(Instant.now()))
                .build();

        postInteractor.initPostPage(postInfoData);

        verify(postPresenter).initPostPage(postInfoData);
    }

    @Test
    void testInitPostPageWithEmptyTitle() {
        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle("")
                .setContent("Test Content")
                .setUsername("testuser")
                .setAvatarUrl("http://example.com/avatar.jpg")
                .setDate(Timestamp.from(Instant.now()))
                .build();

        postInteractor.initPostPage(postInfoData);

        verify(postPresenter).initPostPage(postInfoData);
    }

    @Test
    void testInitPostPageWithNullTitle() {
        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle(null)
                .setContent("Test Content")
                .setUsername("testuser")
                .setAvatarUrl("http://example.com/avatar.jpg")
                .setDate(Timestamp.from(Instant.now()))
                .build();

        postInteractor.initPostPage(postInfoData);

        verify(postPresenter).initPostPage(postInfoData);
    }

    @Test
    void testInitPostPageWithEmptyContent() {
        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle("Test Title")
                .setContent("")
                .setUsername("testuser")
                .setAvatarUrl("http://example.com/avatar.jpg")
                .setDate(Timestamp.from(Instant.now()))
                .build();

        postInteractor.initPostPage(postInfoData);

        verify(postPresenter).initPostPage(postInfoData);
    }

    @Test
    void testInitPostPageWithNullContent() {
        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle("Test Title")
                .setContent(null)
                .setUsername("testuser")
                .setAvatarUrl("http://example.com/avatar.jpg")
                .setDate(Timestamp.from(Instant.now()))
                .build();

        postInteractor.initPostPage(postInfoData);

        verify(postPresenter).initPostPage(postInfoData);
    }

    @Test
    void testInitPostPageWithNullUsername() {
        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle("Test Title")
                .setContent("Test Content")
                .setUsername(null)
                .setAvatarUrl("http://example.com/avatar.jpg")
                .setDate(Timestamp.from(Instant.now()))
                .build();

        postInteractor.initPostPage(postInfoData);

        verify(postPresenter).initPostPage(postInfoData);
    }

    @Test
    void testInitPostPageWithNullAvatarUrl() {
        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle("Test Title")
                .setContent("Test Content")
                .setUsername("testuser")
                .setAvatarUrl(null)
                .setDate(Timestamp.from(Instant.now()))
                .build();

        postInteractor.initPostPage(postInfoData);

        verify(postPresenter).initPostPage(postInfoData);
    }

    @Test
    void testInitPostPageWithNullDate() {
        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle("Test Title")
                .setContent("Test Content")
                .setUsername("testuser")
                .setAvatarUrl("http://example.com/avatar.jpg")
                .setDate(null)
                .build();

        postInteractor.initPostPage(postInfoData);

        verify(postPresenter).initPostPage(postInfoData);
    }

    @Test
    void testInitPostPageWithEmptyUsername() {
        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle("Test Title")
                .setContent("Test Content")
                .setUsername("")
                .setAvatarUrl("http://example.com/avatar.jpg")
                .setDate(Timestamp.from(Instant.now()))
                .build();

        postInteractor.initPostPage(postInfoData);

        verify(postPresenter).initPostPage(postInfoData);
    }

    @Test
    void testInitPostPageWithEmptyAvatarUrl() {
        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle("Test Title")
                .setContent("Test Content")
                .setUsername("testuser")
                .setAvatarUrl("")
                .setDate(Timestamp.from(Instant.now()))
                .build();

        postInteractor.initPostPage(postInfoData);

        verify(postPresenter).initPostPage(postInfoData);
    }
}
