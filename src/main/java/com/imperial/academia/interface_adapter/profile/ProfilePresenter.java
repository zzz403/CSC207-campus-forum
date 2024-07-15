package com.imperial.academia.interface_adapter.profile;

import com.imperial.academia.use_case.profile.ProfileOutputBoundry;
import com.imperial.academia.use_case.profile.ProfileOutputData;

public class ProfilePresenter implements ProfileOutputBoundry {
    private final ProfileViewModel profileViewModel;

    public ProfilePresenter(ProfileViewModel profileViewModel) {
        this.profileViewModel = profileViewModel;
    }


    @Override
    public void present(ProfileOutputData profileOutputData){
        ProfileState profileState = profileViewModel.getProfileState();
        profileState.setEmail(profileOutputData.getEmail());
        profileState.setId(profileOutputData.getId());
        profileState.setUsername(profileOutputData.getUsername());
        profileState.setRole(profileOutputData.getRole());
        profileState.setAvatarUrl(profileOutputData.getAvatarUrl());
        profileState.setRegistrationDate(profileOutputData.getRegistrationDate());
        profileState.setIsMe(profileOutputData.isMe());

        profileViewModel.setProfileState(profileState);
        profileViewModel.firePropertyChanged();
    }


    @Override
    public void presentError(String error){
        profileViewModel.setProfileState(new ProfileState());
        profileViewModel.firePropertyChanged();
    }
}
