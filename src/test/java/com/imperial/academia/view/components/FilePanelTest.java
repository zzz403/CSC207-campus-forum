package com.imperial.academia.view.components;

import com.imperial.academia.entity.chat_message.FileData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FilePanelTest {

    private FileData mockFileData;

    @BeforeEach
    void setUp() {
        mockFileData = mock(FileData.class);
    }

    @Test
    void testFilePanelInitialization() {
        when(mockFileData.getFileName()).thenReturn("testfile.txt");
        when(mockFileData.getFileSize()).thenReturn("2 KB");

        FilePanel filePanel = new FilePanel(mockFileData);
        assertNotNull(filePanel);

        // Ensure the panel is properly added to a frame and displayed
        JFrame frame = new JFrame();
        frame.add(filePanel);
        frame.pack();
        frame.setVisible(false);

        // Allow some time for the GUI to render
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Dispose the frame after rendering
        frame.dispose();
    }
}
