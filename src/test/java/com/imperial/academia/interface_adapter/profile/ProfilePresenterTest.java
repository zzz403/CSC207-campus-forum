package com.imperial.academia.interface_adapter.profile;

import com.imperial.academia.use_case.profile.ProfileOutputData;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class ProfilePresenterTest {
    private ProfileViewModel profileViewModel;
    private ProfileOutputData outputData;
    private ProfilePresenter presenter;
    @BeforeEach
    public void init(){
        profileViewModel = new ProfileViewModel();
        presenter = new ProfilePresenter(profileViewModel);
        outputData = new ProfileOutputData(
                877874918,
                "name",
                "email",
                "role",
                "url",
                new Timestamp(0),
                true
        );
//TODO not finished
    }

    @Test
    void present() {
        presenter.present(outputData);
        assertEquals(877874918, profileViewModel.getProfileState().getId());

    }

    @Test
    void presentError() {
        presenter.presentError("error");
        assertEquals(-1 , profileViewModel.getProfileState().getId());

    }
}