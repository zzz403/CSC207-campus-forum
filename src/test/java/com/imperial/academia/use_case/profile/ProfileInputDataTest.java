package com.imperial.academia.use_case.profile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfileInputDataTest {

    private int userId;

    @BeforeEach
    public void init() {
        userId = 0;
    }

    @Test
    void getUserId() {
        ProfileInputData profileInputData = new ProfileInputData(userId);
        assertEquals(userId, profileInputData.getUserId());
    }
}
