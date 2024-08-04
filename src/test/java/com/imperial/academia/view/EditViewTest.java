package com.imperial.academia.view;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.swing.JFrame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.imperial.academia.interface_adapter.edit.EditViewModel;

class EditViewTest {
    private EditView editView;

    @Mock
    private EditViewModel mockEditViewModel;
    @Mock
    private JFrame mockApplication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        editView = new EditView(mockEditViewModel, mockApplication);
    }

    @Test
    void testEditViewInitializes() {
        // This test will simply check if the EditView initializes components correctly.
        assertNotNull(editView, "EditView should be initialized.");
    }


}
