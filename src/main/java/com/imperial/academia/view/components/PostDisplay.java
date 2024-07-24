import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class PostDisplay {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PostDisplay::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Post Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a panel to hold multiple posts
        JPanel postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
        postsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add several post panels to the postsPanel
        for (int i = 0; i < 5; i++) {
            postsPanel.add(createPostPanel("Sample Post Title " + (i + 1),
                    "This is some content of the post " + (i + 1) + ".",
                    "Author" + (i + 1),
                    "2024-07-24"));
            postsPanel.add(Box.createRigidArea(new Dimension(0, 10)));  // Add some space between posts
        }

        // Add the postsPanel to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(postsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    private static JPanel createPostPanel(String title, String content, String authorId, String creationDate) {
        JPanel postPanel = new JPanel(new GridBagLayout());
        postPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        postPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                showPostDetailDialog(title, content, authorId, creationDate);
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = loadRandomImage();
        if (imageIcon != null) {
            imageLabel.setIcon(imageIcon);
        } else {
            imageLabel.setText("No Image Found");
        }
        imageLabel.setPreferredSize(new Dimension(100, 100));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        postPanel.add(imageLabel, gbc);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        postPanel.add(titleLabel, gbc);

        JLabel contentLabel = new JLabel("<html>" + truncateContent(content) + "</html>");
        contentLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        postPanel.add(contentLabel, gbc);

        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        infoPanel.setOpaque(false);

        JLabel authorLabel = new JLabel("Author ID: " + authorId + " | ");
        authorLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel dateLabel = new JLabel("Creation Date: " + creationDate);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        infoPanel.add(authorLabel);
        infoPanel.add(dateLabel);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        postPanel.add(infoPanel, gbc);

        return postPanel;
    }

    private static String truncateContent(String content) {
        if (content.length() > 50) {
            return content.substring(0, 50) + "...";
        }
        return content;
    }

    private static void showPostDetailDialog(String title, String content, String authorId, String creationDate) {
        JOptionPane.showMessageDialog(null,
                "Title: " + title + "\n" +
                        "Content: " + content + "\n" +
                        "Author ID: " + authorId + "\n" +
                        "Creation Date: " + creationDate,
                "Post Detail",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static ImageIcon loadRandomImage() {
        Random random = new Random();
        int i = random.nextInt(3) + 1;  // Generates a random number between 1 and 3
        String imagePath = "resources/test_image/test_image_" + i + ".jpg";
        ImageIcon imageIcon = new ImageIcon(imagePath);
        if (imageIcon.getIconWidth() == -1) {
            return null; // Image not found
        }
        return imageIcon;
    }
}
