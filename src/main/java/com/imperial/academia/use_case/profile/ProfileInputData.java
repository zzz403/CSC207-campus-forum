package com.imperial.academia.use_case.profile;

public class ProfileInputData {
    private final int userId;
    private final String previousViewName;
    public ProfileInputData(int userId, String previousViewName) {
        this.userId = userId;
        this.previousViewName = previousViewName;
    }

    public int getUserId() {
        return userId;
    }

    public String getPreviousViewName() {
        return previousViewName;
    }
}
