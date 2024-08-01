package com.imperial.academia.view;

import com.imperial.academia.interface_adapter.edit.EditViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

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
