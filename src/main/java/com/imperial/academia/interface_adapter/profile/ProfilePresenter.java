package com.imperial.academia.interface_adapter.profile;

import com.imperial.academia.use_case.profile.ProfileOutputBoundry;
import com.imperial.academia.use_case.profile.ProfileOutputData;

/**
 * The ProfilePresenter class implements the ProfileOutputBoundry interface
 * and is responsible for presenting profile data to the ProfileViewModel.
 */
public class ProfilePresenter implements ProfileOutputBoundry {

    /**
     * The view model for the profile data.
     */
    private final ProfileViewModel profileViewModel;

    /**
     * Constructs a new ProfilePresenter with the specified profile view model.
     *
     * @param profileViewModel the view model for the profile data
     */
    public ProfilePresenter(ProfileViewModel profileViewModel) {
        this.profileViewModel = profileViewModel;
    }

    /**
     * Presents the profile output data by updating the ProfileViewModel.
     *
     * @param profileOutputData the profile data to be presented
     */
    @Override
    public void present(ProfileOutputData profileOutputData) {
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

    /**
     * Presents an error message by resetting the ProfileViewModel state.
     *
     * @param error the error message to be presented
     */
    @Override
    public void presentError(String error) {
        profileViewModel.setProfileState(new ProfileState());
        profileViewModel.firePropertyChanged();
    }
}
