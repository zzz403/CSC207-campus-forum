package com.imperial.academia.use_case.edit;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.entity.user.UserFactory;
import com.imperial.academia.service.UserService;
import com.imperial.academia.session.SessionManager;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.signup.SignupOutputData;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class EditInteractor {
    private final ChangeViewInputBoundary changeViewInteractor;
    private final EditOutputBopundry editPresenter;
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
        EditOutputData editOutputData = new EditOutputData(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getAvatarUrl(),
                user.getRegistrationDate()
        );
        editPresenter.present(editOutputData);
        changeViewInteractor.changeView("edit");
    }

    public void update(EditInoutData editInoutData){
        try {
            editPresenter.prepareFailView(null);
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
                User updatedUser = userFactory.create(
                        SessionManager.getCurrentUser().getId(),
                        editInoutData.getUsername(),
                        editInoutData.getPassword(),
                        editInoutData.getEmail(),
                        SessionManager.getCurrentUser().getRole(),
                        SessionManager.getCurrentUser().getAvatarUrl(),//TODO change avatar???
                        SessionManager.getCurrentUser().getRegistrationDate(),
                        now
                );
                SessionManager.setCurrentUser(updatedUser);
                userService.update(updatedUser);
                editPresenter.prepareSuccessView();
                changeViewInteractor.changeView("profile");//TODO navigate to login ?
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
