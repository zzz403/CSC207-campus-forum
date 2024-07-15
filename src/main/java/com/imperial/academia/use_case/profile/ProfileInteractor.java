package com.imperial.academia.use_case.profile;

import java.sql.SQLException;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.service.UserService;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.session.SessionManager;

public class ProfileInteractor implements ProfileInputBoundry {

    private final ChangeViewInputBoundary changeViewInteractor = UsecaseFactory.getChangeViewInteractor();

    private final ProfileOutputBoundry profilepresenter;

    public ProfileInteractor(ProfileOutputBoundry profilepresenter) {
        this.profilepresenter = profilepresenter;
    }

    public void excute(ProfileInputData profileInputData) {
        UserService userService = ServiceFactory.getUserService();
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
                profilepresenter.present(profileOutputData);
            } else {
                profilepresenter.presentError("User not found");
            }

            changeViewInteractor.changeView("profile");
            
        } catch (SQLException e) {
            profilepresenter.presentError(e.getMessage());
        }
    }
}
