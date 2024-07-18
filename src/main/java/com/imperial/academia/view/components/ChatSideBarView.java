package com.imperial.academia.view.components;

import com.imperial.academia.interface_adapter.chat.ChatSideBarController;
import com.imperial.academia.interface_adapter.chat.ChatSideBarViewModel;
import com.imperial.academia.entity.chat_group.ChatGroupDTO;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * ChatSideBarView class represents the sidebar for displaying chat groups.
 */
public class ChatSideBarView extends JPanel {
    private final JPanel chatListPanel;
    private final JTextField searchField;
    private final ChatSideBarController chatSideBarController = new ChatSideBarController();
    private final Map<Integer,ImageIcon> avatarCache = new HashMap<>();
    ChatSideBarViewModel chatSideBarViewModel;

    /**
     * Constructs a ChatSideBarView with the specified controller and view model.
     *
     * @param chatSideBarViewModel  The view model to manage the state of the
     *                              sidebar.
     */
    public ChatSideBarView(ChatSideBarViewModel chatSideBarViewModel) {
        this.chatSideBarViewModel = chatSideBarViewModel;
        try {
            BufferedImage avatarImage = ImageIO.read(new File("resources/avatar/default/group_chat.png"));
            Image scaledAvatarImage = avatarImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            avatarCache.put(0, new ImageIcon(scaledAvatarImage));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new BorderLayout());

        // Create search panel
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Create search field with icon
        searchField = new JTextField("Search...") {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque()) {
                    int w = getWidth();
                    int h = getHeight();
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getBackground());
                    g2.fillRoundRect(0, 0, w, h, 20, 20);
                    g2.dispose();
                }
                super.paintComponent(g);
            }

            @Override
            public void setBorder(Border border) {
                super.setBorder(new EmptyBorder(5, 5, 5, 5));
            }
        };
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setForeground(Color.GRAY);
        searchField.setPreferredSize(new Dimension(300, 30)); // Increase the width
        searchField.setBorder(new EmptyBorder(0, 20, 0, 0));
        searchField.setBackground(Color.WHITE);
        searchField.setOpaque(false);

        JPanel searchContainer = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(new Color(220, 220, 220));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
        };
        searchContainer.setBackground(Color.WHITE);
        searchContainer.setOpaque(false);
        searchContainer.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Load and add search icon
        try {
            BufferedImage searchIconImage = ImageIO.read(new File("resources/icons/search_icon.png"));
            Image scaledSearchIconImage = searchIconImage.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
            JLabel searchIcon = new JLabel(new ImageIcon(scaledSearchIconImage));
            searchIcon.setBorder(new EmptyBorder(0, 10, 0, 10));
            searchContainer.add(searchIcon, BorderLayout.WEST);
        } catch (IOException e) {
            e.printStackTrace();
        }

        searchContainer.add(searchField, BorderLayout.CENTER);

        // Add focus listener to search field
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (searchField.getText().equals("Search...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });

        // Add document listener to search field
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                searchChatGroups();
            }

            public void removeUpdate(DocumentEvent e) {
                searchChatGroups();
            }

            public void changedUpdate(DocumentEvent e) {
                searchChatGroups();
            }

            private void searchChatGroups() {
                SwingUtilities.invokeLater(() -> {
                    String searchText = searchField.getText();
                    if (searchText.equals("Search...")) {
                        chatSideBarController.execute("");
                    } else {
                        chatSideBarController.execute(searchText);
                    }
                });
            }
        });

        searchPanel.add(searchContainer, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.NORTH);

        // Create chat list panel
        chatListPanel = new JPanel();
        chatListPanel.setLayout(new BoxLayout(chatListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(chatListPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        // Add property change listener to view model
        chatSideBarViewModel.addPropertyChangeListener(evt -> {
            if (evt.getPropertyName().equals("chatSideBarState")) {
                displayChatGroups(chatSideBarViewModel.getState().getChatGroups());
            } else {
                chatListPanel.removeAll();
            }
        });
    }

    /**
     * Displays the list of chat groups in the sidebar.
     *
     * @param chatGroups The list of chat groups to display.
     */
    public void displayChatGroups(List<ChatGroupDTO> chatGroups) {
        chatListPanel.removeAll();
        int index = 0;
        for (ChatGroupDTO chatGroup : chatGroups) {
            JPanel chatItem = createChatItem(chatGroup, index);
            chatListPanel.add(chatItem);
            index++;
        }
        revalidate();
        repaint();
    }

    /**
     * Creates a chat item panel for a given chat group.
     *
     * @param chatGroup The chat group data transfer object.
     * @param index     The index of the chat group in the list.
     * @return A JPanel representing the chat item.
     */
    private JPanel createChatItem(ChatGroupDTO chatGroup, int index) {
        JPanel chatItem = new JPanel(new BorderLayout());
        chatItem.setPreferredSize(new Dimension(350, 80));
        chatItem.setMaximumSize(new Dimension(Short.MAX_VALUE, 80));

        // Determine initial background color based on index
        Color originalColor = (index % 2 == 0) ? Color.WHITE : new Color(245, 245, 245);
        chatItem.setBackground(originalColor);

        // Avatar
        JLabel avatar = new JLabel();
        avatar.setPreferredSize(new Dimension(70, 70));
        avatar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around avatar
        chatItem.add(avatar, BorderLayout.WEST);

        // Load and resize avatar image
        if (chatGroup.isGroup()){
            avatar.setIcon(avatarCache.get(0));
        }else{
            if (avatarCache.containsKey(chatGroup.getId())){
                avatar.setIcon(avatarCache.get(chatGroup.getId()));
            }else{
                try {
                    BufferedImage avatarImage = ImageIO.read(new File(chatGroup.getAvatarUrl()));
                    BufferedImage roundedAvatarImage = makeRoundedCorner(avatarImage);
                    Image scaledAvatarImage = roundedAvatarImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    avatar.setIcon(new ImageIcon(scaledAvatarImage));
                    avatarCache.put(chatGroup.getId(), new ImageIcon(scaledAvatarImage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Chat Info
        JPanel chatInfoPanel = new JPanel();
        chatInfoPanel.setLayout(new BorderLayout());
        chatInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 15, 10));
        chatInfoPanel.setOpaque(false);
        chatItem.add(chatInfoPanel, BorderLayout.CENTER);

        // Group Name and Time
        JPanel nameTimePanel = new JPanel(new BorderLayout());
        nameTimePanel.setOpaque(false);
        JLabel groupNameLabel = new JLabel(chatGroup.getGroupName());
        groupNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        groupNameLabel.setForeground(new Color(44, 62, 80));

        JLabel timeLabel = new JLabel(formatTimestamp(chatGroup.getLastMessageTime()));
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        timeLabel.setForeground(new Color(189, 195, 199));
        nameTimePanel.add(groupNameLabel, BorderLayout.WEST);
        nameTimePanel.add(timeLabel, BorderLayout.EAST);

        nameTimePanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        chatInfoPanel.add(nameTimePanel, BorderLayout.NORTH);

        // Last Message
        JLabel lastMessageLabel = new JLabel(chatGroup.getLastMessage());
        lastMessageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        lastMessageLabel.setForeground(new Color(127, 140, 141));
        chatInfoPanel.add(lastMessageLabel, BorderLayout.SOUTH);

        // Mouse hover effect
        chatItem.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chatItem.setBackground(new Color(173, 216, 230)); // Light blue background on hover
                chatItem.repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chatItem.setBackground(originalColor);
                chatItem.repaint();
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Remove focus from search field
                searchField.setFocusable(false);
                searchField.setFocusable(true);
                chatSideBarController.selectChatGroup(chatGroup.getId());
            }
        });

        return chatItem;
    }

    /**
     * Format the timestamp for display.
     * @param timestamp the timestamp to format
     * @return a formatted timestamp string
     */
    private String formatTimestamp(Timestamp timestamp) {
        Calendar messageTime = Calendar.getInstance();
        messageTime.setTimeInMillis(timestamp.getTime());

        Calendar now = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.ENGLISH);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, h:mm a", Locale.ENGLISH);
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy MMM dd, h:mm a", Locale.ENGLISH);

        if (now.get(Calendar.YEAR) == messageTime.get(Calendar.YEAR)) {
            if (now.get(Calendar.DAY_OF_YEAR) == messageTime.get(Calendar.DAY_OF_YEAR)) {
                return timeFormat.format(messageTime.getTime()); // Today
            } else if (now.get(Calendar.DAY_OF_YEAR) - messageTime.get(Calendar.DAY_OF_YEAR) == 1) {
                return "Yesterday, " + timeFormat.format(messageTime.getTime()); // Yesterday
            } else {
                return dateFormat.format(messageTime.getTime()); // This year but not today or yesterday
            }
        } else {
            return yearFormat.format(messageTime.getTime()); // Different year
        }
    }

    /**
     * Makes the corners of the image rounded.
     *
     * @param image the image to make rounded
     * @return the image with rounded corners
     */
    private BufferedImage makeRoundedCorner(BufferedImage image) {
        int cornerRadius = 275; // The radius for the rounded corners
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        // Enable anti-aliasing for smooth corners
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

        // Composite the image on top using the white shape as the alpha source
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
    }
}
