package com.imperial.academia.use_case.profile;

import java.sql.SQLException;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.service.UserService;
import com.imperial.academia.session.SessionManager;

public class ProfileInteractor implements ProfileInputBoundry {
    private final UserService userService;
    private final ProfileOutputBoundry profilePresenter;

    public ProfileInteractor( ProfileOutputBoundry profilePresenter) {
        this.userService = ServiceFactory.getUserService();

        this.profilePresenter = profilePresenter;
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
                        user.getRegistrationDate(),
                        SessionManager.getCurrentUser().getId() == user.getId()
                );
                profilePresenter.present(profileOutputData);
            } else {
                profilePresenter.presentError("User not found");
            }
        } catch (SQLException e) {
            profilePresenter.presentError(e.getMessage());
        }
    }
}
