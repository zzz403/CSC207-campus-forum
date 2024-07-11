package com.imperial.academia.interface_adapter.profile;

import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.use_case.profile.ProfileOutputBoundry;
import com.imperial.academia.use_case.profile.ProfileOutputData;

public class ProfilePresenter implements ProfileOutputBoundry {
    private final ProfileViewModel profileViewModel;
    private final ViewManagerModel viewManagerModel;

    public ProfilePresenter(ProfileViewModel profileViewModel, ViewManagerModel viewManagerModel) {
        this.profileViewModel = profileViewModel;
        this.viewManagerModel = viewManagerModel;
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
        viewManagerModel.setActiveView("profile");
        viewManagerModel.firePropertyChanged();
    }


    @Override
    public void changeView(String viewName){
        viewManagerModel.setActiveView(viewName);
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void presentError(String error){
        profileViewModel.setProfileState(new ProfileState());
        profileViewModel.firePropertyChanged();
        viewManagerModel.setActiveView("profile");
        viewManagerModel.firePropertyChanged();
    }
}
