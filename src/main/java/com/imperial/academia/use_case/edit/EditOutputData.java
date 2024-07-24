package com.imperial.academia.use_case.edit;

import com.imperial.academia.use_case.profile.ProfileOutputData;

import java.sql.Timestamp;

public class EditOutputData {
    /**
     * The ID of the user.
     */
    private final int id;

    /**
     * The username of the user.
     */
    private final String username;

    /**
     * The email of the user.
     */
    private final String email;

    /**
     * The role of the user.
     */
    private final String role;

    /**
     * The avatar URL of the user.
     */
    private final String avatarUrl;

    /**
     * The registration date of the user.
     */
    private final Timestamp registrationDate;


    public EditOutputData(int id, String username, String email, String role, String avatarUrl, Timestamp registrationDate){
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

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

}
