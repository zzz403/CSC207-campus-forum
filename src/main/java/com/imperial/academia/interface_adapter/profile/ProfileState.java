package com.imperial.academia.interface_adapter.profile;

import java.sql.Timestamp;

public class ProfileState {
    private int id = -1;
    private String username = "";
    private String email = "";
    private String role = "";
    private String avatarUrl = "";
    private Timestamp registrationDate = new Timestamp(System.currentTimeMillis());

    public ProfileState (ProfileState copy){
        this.id = copy.id;
        this.username = copy.username;
        this.email = copy.email;
        this.role = copy.role;
        this.avatarUrl = copy.avatarUrl;
        this.registrationDate = copy.registrationDate;
    }
    public ProfileState(){}

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



    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }



}
