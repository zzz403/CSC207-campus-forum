package com.imperial.academia.interface_adapter.profile;

import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.use_case.profile.ProfileInputBoundry;
import com.imperial.academia.use_case.profile.ProfileInputData;

public class ProfileController{
    private final ProfileInputBoundry profileInteractor;

    public ProfileController() {
        this.profileInteractor = UsecaseFactory.getProfileInteractor();
    }


    public void showProfile(int userId){
        ProfileInputData profileInputData = new ProfileInputData(userId);
        profileInteractor.excute(profileInputData);
    }
}
