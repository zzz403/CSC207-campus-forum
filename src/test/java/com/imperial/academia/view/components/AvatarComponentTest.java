package com.imperial.academia.view.components;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.imperial.academia.interface_adapter.profile.ProfileController;

public class AvatarComponentTest {

    private ProfileController mockProfileController;

    @BeforeEach
    public void setUp() {
        // Initialize the mock ProfileController
        mockProfileController = mock(ProfileController.class);
    }

    @Test
    public void testAvatarComponentClick() {
        // Create an AvatarComponent with the mock ProfileController
        AvatarComponent avatarComponent = new AvatarComponent(1, new ImageIcon("path/to/test/icon.png"), mockProfileController);

        // Simulate a mouse click event
        MouseEvent clickEvent = new MouseEvent(avatarComponent, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false);
        for (MouseListener listener : avatarComponent.getMouseListeners()) {
            listener.mouseClicked(clickEvent);
        }

        // Verify that showProfile was called with the correct user ID
        verify(mockProfileController).showProfile(1);
    }
}
