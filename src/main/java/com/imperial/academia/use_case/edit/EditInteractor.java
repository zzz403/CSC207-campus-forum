package com.imperial.academia.use_case.edit;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.entity.user.UserFactory;
import com.imperial.academia.service.UserService;
import com.imperial.academia.session.SessionManager;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.profile.ProfileInputData;
import com.imperial.academia.use_case.profile.ProfileInteractor;
import com.imperial.academia.use_case.profile.ProfileOutputData;
import com.imperial.academia.use_case.signup.SignupOutputData;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class EditInteractor {
    private final ChangeViewInputBoundary changeViewInteractor;
    private final EditOutputBoundry editPresenter;
    private final UserFactory userFactory;
    private final UserService userService;

    public EditInteractor(EditOutputBoundry editPresenter,UserFactory userFactory){
        this.editPresenter = editPresenter;
        this.userService = ServiceFactory.getUserService();
        this.userFactory = userFactory;
        this.changeViewInteractor = UsecaseFactory.getChangeViewInteractor();
    }

    public void excute(){
        User user = SessionManager.getCurrentUser();
        if (user == null){
            changeViewInteractor.changeView("login");
        } else {
            EditOutputData editOutputData = new EditOutputData(
                    user.getUsername(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getAvatarUrl()
            );
            editPresenter.present(editOutputData);
            changeViewInteractor.changeView("edit");
        }
    }

    public void update(EditInputData editInputData){
        try {
            editPresenter.prepareFailView(null);//TODO same as before information handling
            if (editInputData.getUsername().length() <= 6) {
                editPresenter.prepareFailView("Username must be longer than 6 characters.");
            } else if (userService.existsByUsername(editInputData.getUsername())) {
                editPresenter.prepareFailView("User already exists.");
            } else if (editInputData.getPassword().length() <= 6) {
                editPresenter.prepareFailView("Password must be longer than 6 characters.");
            } else if (!editInputData.getPassword().equals(editInputData.getRepeatPassword())) {
                editPresenter.prepareFailView("Repeat Passwords don't match.");
            } else if (!isValidEmail(editInputData.getEmail())) {
                editPresenter.prepareFailView("Email is not valid.");
            } else if (userService.existsByEmail(editInputData.getEmail())) {
                editPresenter.prepareFailView("Email already used.");
            } else {
                LocalDateTime now = LocalDateTime.now();
                User oldUser = SessionManager.getCurrentUser();
                if (oldUser == null){
                    changeViewInteractor.changeView("login");
                } else {
                    User updatedUser = userFactory.create(
                            oldUser.getId(),
                            editInputData.getUsername(),
                            editInputData.getPassword(),
                            editInputData.getEmail(),
                            oldUser.getAvatarUrl(),//TODO change avatar???  chat window interactor
                            oldUser.getRegistrationDate(),
                            now
                    );
                    SessionManager.setCurrentUser(updatedUser);
                    userService.update(updatedUser);
                    ProfileInputData profileInputData = new ProfileInputData(updatedUser.getId());
                    UsecaseFactory.getProfileInteractor().execute(profileInputData);
                }
            }
        } catch (SQLException e) {
            editPresenter.prepareFailView("An error occurred during signup: " + e.getMessage());
        }
    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }
}
