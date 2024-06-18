package com.imperial.academia.view;

import javax.swing.*;
import java.awt.*;

public class ForumView extends JPanel {
    public final String viewName = "forum";

    public ForumView() {
        setLayout(new BorderLayout());
        add(new JLabel("Forum View", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
