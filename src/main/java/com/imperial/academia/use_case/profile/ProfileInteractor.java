package com.imperial.academia.use_case.profile;

import java.sql.SQLException;

import com.imperial.academia.entity.user.User;
import com.imperial.academia.service.UserService;

public class ProfileInteractor implements ProfileInputBoundry {
    private final UserService userService;
    private final ProfileOutputBoundry profilepresenter;

    public ProfileInteractor(UserService userService, ProfileOutputBoundry profilepresenter) {
        this.userService = userService;
        this.profilepresenter = profilepresenter;
    }

    public void excute(ProfileInputData profileInputData) {
        try {
            User user = userService.get(profileInputData.getUserId());
            if (user != null) {
                ProfileOutputData profileOutputData = new ProfileOutputData(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRole(),
                        user.getAvatarUrl(),
                        user.getRegistrationDate());
                profilepresenter.present(profileOutputData);
            } else {
                profilepresenter.presentError("User not found");
            }
        } catch (SQLException e) {
            profilepresenter.presentError(e.getMessage());
        }
    }
}
