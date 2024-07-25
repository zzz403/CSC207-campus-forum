package com.imperial.academia.use_case.edit;

import com.imperial.academia.use_case.profile.ProfileOutputData;

import java.sql.Timestamp;

public class EditOutputData {


    /**
     * The username of the user.
     */
    private final String username;
    private final String password;

    /**
     * The email of the user.
     */
    private final String email;


    /**
     * The role of the user.
     */

    private final String avatarUrl;

    /**
     * The registration date of the user.
     */




    public EditOutputData( String username,String password, String email, String avatarUrl){
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatarUrl = avatarUrl;
    }



    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }


    public String getAvatarUrl() {
        return avatarUrl;
    }


}
