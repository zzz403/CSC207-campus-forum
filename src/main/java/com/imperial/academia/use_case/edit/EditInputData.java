package com.imperial.academia.use_case.edit;

public class EditInputData {
    private final String username;
    private final String password;
    private final String avatarURL;
    private final String email;

    public EditInputData(String username, String password, String avatarURL, String email) {
        this.username = username;
        this.password = password;
        this.avatarURL = avatarURL;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public String getEmail() {
        return email;
    }
}
