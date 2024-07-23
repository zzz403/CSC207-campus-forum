package com.imperial.academia.use_case.profile;

import java.sql.SQLException;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.service.UserService;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.session.SessionManager;

/**
 * The ProfileInteractor class implements the ProfileInputBoundry interface
 * and provides the logic for handling profile-related operations.
 */
public class ProfileInteractor implements ProfileInputBoundry {

    /**
     * The interactor responsible for changing the view.
     */
    private final ChangeViewInputBoundary changeViewInteractor;

    /**
     * The presenter for profile operations.
     */
    private final ProfileOutputBoundry profilePresenter;

    /**
     * The service for user operations.
     */
    private final UserService userService;

    /**
     * Constructs a new ProfileInteractor with the specified profile presenter.
     *
     * @param profilePresenter the presenter for profile operations
     */
    public ProfileInteractor(ProfileOutputBoundry profilePresenter) {
        this.profilePresenter = profilePresenter;
        this.userService = ServiceFactory.getUserService();
        this.changeViewInteractor = UsecaseFactory.getChangeViewInteractor();
    }
    public ProfileInteractor(ProfileOutputBoundry profilePresenter, UserService userService, ChangeViewInputBoundary changeViewInteractor) {
        this.profilePresenter = profilePresenter;
        this.userService = userService;
        this.changeViewInteractor = changeViewInteractor;
    }

    /**
     * Executes the profile operation with the given profile input data.
     * This involves retrieving user data, presenting the data, and changing the view.
     *
     * @param profileInputData the data required to perform the profile operation
     */
    public void execute(ProfileInputData profileInputData) {
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

            changeViewInteractor.changeView("profile");

        } catch (SQLException e) {
            profilePresenter.presentError(e.getMessage());
        }
    }
}
