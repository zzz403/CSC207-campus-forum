package com.imperial.academia.use_case.session;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "SessionOutputData{userId=" + userId + ", username='" + username + "', avatarUrl='" + avatarUrl + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionOutputData that = (SessionOutputData) o;
        return userId == that.userId &&
                username.equals(that.username) &&
                avatarUrl.equals(that.avatarUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, avatarUrl);
    }
}
