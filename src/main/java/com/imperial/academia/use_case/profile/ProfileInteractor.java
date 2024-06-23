package com.imperial.academia.use_case.profile;

import com.imperial.academia.entity.user.User;
import com.imperial.academia.entity.user.UserFactory;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.service.UserService;

import java.sql.SQLException;

public class ProfileInteractor implements ProfileInputBoundry{
    private final UserService userService;
    private final ProfileOutputBoundry profilepresenter;

    public ProfileInteractor(UserService userService, ProfileOutputBoundry profilepresenter) {
        this.userService = userService;
        this.profilepresenter = profilepresenter;
    }

    public void excute(ProfileInputData profileInputData){
        try{
            User user = userService.get(profileInputData.getUserId());
            if (user != null){
                ProfileOutputData profileOutputData = new ProfileOutputData(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRole(),
                        user.getAvatarUrl(),
                        user.getRegistrationDate(),
                        profileInputData.getPreviousViewName()
                );
                profilepresenter.present(profileOutputData);
            } else{
                profilepresenter.presentError("User not found");
            }
        } catch (SQLException e) {
            profilepresenter.presentError(e.getMessage());
        }
    }
}
