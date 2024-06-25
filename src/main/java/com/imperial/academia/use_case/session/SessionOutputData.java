package com.imperial.academia.use_case.session;

public class SessionOutputData {
    private final int userId;
    private final String username;
    private final String avatarUrl;

    public SessionOutputData(int userId, String username, String avatarUrl) {
        this.userId = userId;
        this.username = username;
        this.avatarUrl = avatarUrl;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
