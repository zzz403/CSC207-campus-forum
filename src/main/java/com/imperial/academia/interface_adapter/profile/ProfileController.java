package com.imperial.academia.interface_adapter.profile;

import com.imperial.academia.use_case.profile.ProfileInputBoundry;
import com.imperial.academia.use_case.profile.ProfileInputData;

public class ProfileController{
    private final ProfileInputBoundry profileInteractor;

    public ProfileController(ProfileInputBoundry profileInteractor) {
        this.profileInteractor = profileInteractor;
    }


    public void showProfile(int userId){
        ProfileInputData profileInputData = new ProfileInputData(userId);
        profileInteractor.excute(profileInputData);
    }
}
