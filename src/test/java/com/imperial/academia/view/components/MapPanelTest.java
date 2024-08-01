package com.imperial.academia.view.components;

import com.imperial.academia.entity.chat_message.MapData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MapPanelTest {

    private MapData mockMapData;
    private BufferedImage mockMapImage;

    @BeforeEach
    void setUp() {
        mockMapData = mock(MapData.class);
        mockMapImage = new BufferedImage(300, 200, BufferedImage.TYPE_INT_ARGB);
    }

    @Test
    void testMapPanelInitialization() {
        when(mockMapData.getLocationInfo()).thenReturn("Imperial College London");

        MapPanel mapPanel = new MapPanel(mockMapData, mockMapImage, false);
        assertNotNull(mapPanel);

        // Ensure the panel is properly added to a frame and displayed
        JFrame frame = new JFrame();
        frame.add(mapPanel);
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
