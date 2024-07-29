package com.imperial.academia.interface_adapter.edit;

import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.edit.EditInputBoundary;
import com.imperial.academia.use_case.edit.EditInputData;

import java.io.File;

/**
 * Controller for handling edit operations related to user profiles.
 * This class connects the edit-related user interface actions with the corresponding use case operations.
 */
public class EditController {
    private final EditInputBoundary editInteractor;
    private final ChangeViewInputBoundary changeViewInteractor;

    /**
     * Constructs an EditController with necessary dependencies initialized through the UsecaseFactory.
     */
    public EditController() {
        this.editInteractor = UsecaseFactory.getEditInteractor();
        this.changeViewInteractor = UsecaseFactory.getChangeViewInteractor();
    }

    /**
     * Triggers the execution of edit operations without any modifications.
     */
    public void execute() {
        editInteractor.execute();
    }

    /**
     * Updates user information based on provided input data.
     *
     * @param username       User's new username.
     * @param password       User's new password.
     * @param repeatPassword Confirmation of the user's new password.
     * @param avatarURL      URL of the user's new avatar.
     * @param email          User's new email address.
     */
    public void update(String username, String password, String repeatPassword, String avatarURL, String email) {
        EditInputData editInputData = new EditInputData(username, password, repeatPassword, avatarURL, email);
        editInteractor.update(editInputData);
    }

    /**
     * Cancels the edit operation and changes the view back to the profile view.
     */
    public void cancel() {
        changeViewInteractor.changeView("profile");
    }

    /**
     * Changes the user's avatar to a new file selected by the user.
     *
     * @param userId      The unique identifier of the user.
     * @param selectedFile The file that contains the new avatar image.
     */
    public void changeAvatar(int userId, File selectedFile) {
        editInteractor.changeAvatar(userId, selectedFile);
    }
}
