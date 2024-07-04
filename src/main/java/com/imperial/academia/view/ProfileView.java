package com.imperial.academia.view;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.imperial.academia.interface_adapter.profile.ProfileController;
import com.imperial.academia.interface_adapter.profile.ProfileState;
import com.imperial.academia.interface_adapter.profile.ProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ProfileView extends JPanel {
    public final String viewName = "profile";
    private final Map<String, ImageIcon> imageCache= new HashMap<>();
    // Constructor
    public ProfileView(ProfileController profileController, ProfileViewModel profileViewModel) {
        super(new BorderLayout());  // Set the layout of ProfileView to BorderLayout for simplicity

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(400, 100));

        // default image
        JPanel avatarPanel = new JPanel(new BorderLayout());
        avatarPanel.setBackground(new Color(255, 225, 120));
        ImageIcon originalIcon = new ImageIcon("resources/avatar/default_avatar.png");
        Image resizedImage = originalIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(resizedIcon);
        avatarPanel.add(imageLabel);
        topPanel.add(avatarPanel, BorderLayout.WEST);

        JPanel infoPanel = new JPanel(new GridLayout(0, 1));
        infoPanel.setBackground(new Color(255, 225, 120));
        JLabel nameLabel = new JLabel("          UserName: UserName");// TODO need fix
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel emailLabel = new JLabel("          Email: xxx@mail.com");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JLabel idLabel = new JLabel("          User ID: UserId");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JLabel roleLabel = new JLabel("          Role : ");
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JLabel registrationDateLabel = new JLabel("          Member since ");
        registrationDateLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        infoPanel.add(nameLabel, BorderLayout.CENTER);
        infoPanel.add(emailLabel, BorderLayout.CENTER);
        infoPanel.add(idLabel, BorderLayout.CENTER);
        infoPanel.add(roleLabel, BorderLayout.CENTER);
        infoPanel.add(registrationDateLabel, BorderLayout.CENTER);

        topPanel.add(infoPanel, BorderLayout.CENTER);

        // TODO change bottom to user's post
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(new Color(58, 185, 232));
        for (int i = 0; i < 3000; i++) {
            bottomPanel.add(new JLabel("Label " + i));
        }

        JScrollPane scrollPane = new JScrollPane(bottomPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Set constraints for the top panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 0.2;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(topPanel, constraints);

        // Set constraints for the scroll pane
        constraints.gridy = 1;
        constraints.weighty = 0.8;
        mainPanel.add(scrollPane, constraints);

        this.add(mainPanel, BorderLayout.CENTER);  // Add mainPanel to ProfileView

        profileViewModel.addPropertyChangeListener(evt ->{
            if ("state".equals((evt.getPropertyName()))){
                ProfileState state = (ProfileState) evt.getNewValue();
                // set the new image
                if (imageCache.containsKey(state.getAvatarUrl())){
                    imageLabel.setIcon(imageCache.get(state.getAvatarUrl()));
                }else{
                ImageIcon UserIcon = new ImageIcon(state.getAvatarUrl());
                Image resizedUserImage = UserIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                ImageIcon resizedUserIcon = new ImageIcon(resizedUserImage);
                imageLabel.setIcon(resizedUserIcon);
                imageCache.put(state.getAvatarUrl(), resizedUserIcon);
                }

                // set the user info
                nameLabel.setText("          UserName: " + state.getUsername());
                emailLabel.setText("          Email: " + state.getEmail());
                idLabel.setText("          User ID: " + state.getId());
                roleLabel.setText("          Role : " + state.getRole());
                registrationDateLabel.setText("          Member since " + state.getRegistrationDate().toString().substring(0,10));
                //TODO  date formate need to be fixed
//                state.getRegistrationDate();
                // TODO info and post need to be done
            }
        });
    }
}
