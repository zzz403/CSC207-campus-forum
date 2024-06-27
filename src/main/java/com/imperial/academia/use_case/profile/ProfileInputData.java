package com.imperial.academia.use_case.profile;

public class ProfileInputData {
    private final int userId;
    public ProfileInputData(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}
