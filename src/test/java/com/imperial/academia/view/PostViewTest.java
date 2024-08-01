package com.imperial.academia.view;

import com.imperial.academia.interface_adapter.post.PostViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JFrame;

class PostViewTest {
    private PostView postView;

    @Mock
    private PostViewModel mockPostViewModel;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        JFrame jFrame = new JFrame();
        postView = new PostView(mockPostViewModel, jFrame);
    }

    @Test
    void testPostViewInitializes() {
        // This test will simply check if the PostView initializes components correctly.
        assertNotNull(postView, "PostView should be initialized.");
    }

}