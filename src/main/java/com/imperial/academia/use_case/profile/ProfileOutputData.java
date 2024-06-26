package com.imperial.academia.use_case.profile;

import java.sql.Timestamp;

public class ProfileOutputData {
    private final int id;
    private final String username;
    private final String email;
    private final String role;
    private final String avatarUrl;
    private final Timestamp registrationDate;

    public ProfileOutputData(int id, String username, String email, String role, String avatarUrl, Timestamp registrationDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.avatarUrl = avatarUrl;
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
