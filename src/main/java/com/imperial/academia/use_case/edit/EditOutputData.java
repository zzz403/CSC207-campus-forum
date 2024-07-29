package com.imperial.academia.use_case.edit;

import com.imperial.academia.use_case.profile.ProfileOutputData;

import java.sql.Timestamp;

public class EditOutputData {


    /**
     * The username of the user.
     */
    private final int userId;
    private final String userName;
    private final String password;
    private final String email;
    private final String avatarURL;


    public EditOutputData(int userId, String userName, String password, String email, String avatarURL){
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.avatarURL = avatarURL;
    }


    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatarURL() {
        return avatarURL;
    }
}
