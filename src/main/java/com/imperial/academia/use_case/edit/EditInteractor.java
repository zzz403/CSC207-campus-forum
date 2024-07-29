package com.imperial.academia.use_case.edit;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.entity.user.UpdateUserFactory;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.service.FileService;
import com.imperial.academia.service.UserService;
import com.imperial.academia.session.SessionManager;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.profile.ProfileInputData;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Interactor class for editing user profile details. This class handles the business logic
 * associated with user profile edits, including validation, update, and avatar changes.
 */
public class EditInteractor implements EditInputBoundary {
    private final ChangeViewInputBoundary changeViewInteractor;
    private final EditOutputBoundary editPresenter;
    private final UpdateUserFactory updateUserFactory;
    private final UserService userService;
    private final FileService fileService;

    /**
     * Constructs an EditInteractor with the necessary collaborators.
     * @param editPresenter Interface to communicate with the presentation layer.
     * @param updateUserFactory Factory to create updated user instances.
     */
    public EditInteractor(EditOutputBoundary editPresenter, UpdateUserFactory updateUserFactory) {
        this.editPresenter = editPresenter;
        this.userService = ServiceFactory.getUserService();
        this.updateUserFactory = updateUserFactory;
        this.changeViewInteractor = UsecaseFactory.getChangeViewInteractor();
        this.fileService = ServiceFactory.getFileService();
    }

    /**
     * Executes the initial data loading for the edit view by fetching current user data.
     * If no user is logged in, it redirects to the login view.
     */
    @Override
    public void execute() {
        User user = SessionManager.getCurrentUser();
        if (user == null) {
            changeViewInteractor.changeView("login");
        } else {
            EditOutputData editOutputData = new EditOutputData(user.getId(), user.getUsername(), user.getPassword(),
                    user.getEmail(), user.getAvatarUrl());
            editPresenter.present(editOutputData);
            changeViewInteractor.changeView("edit");
        }
    }

    /**
     * Updates user profile details based on provided edit data.
     * Validates the input data before updating the user profile in the database.
     * @param editInputData Data object containing the edited values.
     */
    @Override
    public void update(EditInputData editInputData) {
        User oldUser = SessionManager.getCurrentUser();
        if (oldUser == null) {
            changeViewInteractor.changeView("login");
        } else {
            try {
                validateAndUpdateUser(editInputData, oldUser);
            } catch (SQLException e) {
                editPresenter.prepareFailView("An error occurred during update: " + e.getMessage(), null);
            }
        }
    }

    /**
     * Validates the input data and updates the user information if validation passes.
     * @param editInputData Input data from the user form.
     * @param oldUser Current user data from the session.
     * @throws SQLException If updating the user fails.
     */
    private boolean validateAndUpdateUser(EditInputData editInputData, User oldUser) throws SQLException {
        if (!validateInputData(editInputData, oldUser)) return false;

        LocalDateTime now = LocalDateTime.now();
        User updatedUser = updateUserFactory.create(oldUser.getId(), editInputData.getUsername(), editInputData.getPassword(),
                editInputData.getEmail(), oldUser.getRole(), oldUser.getAvatarUrl(),
                oldUser.getRegistrationDate(), now);

        userService.update(updatedUser);
        SessionManager.setCurrentUser(updatedUser);

        // Trigger the profile interactor to reflect changes
        ProfileInputData profileInputData = new ProfileInputData(updatedUser.getId());
        UsecaseFactory.getProfileInteractor().execute(profileInputData);
        return true;
    }

    /**
     * Performs validation checks on the user input.
     * @param editInputData Data from the edit form.
     * @param oldUser Existing user data.
     * @return true if all validations pass, false otherwise.
     * @throws SQLException If user existence checks fail.
     */
    private boolean validateInputData(EditInputData editInputData, User oldUser) throws SQLException {
        EditOutputData editOutputData = new EditOutputData(oldUser.getId(), oldUser.getUsername(), oldUser.getPassword(),
                oldUser.getEmail(), oldUser.getAvatarUrl());

        // Perform individual field validations
        if (editInputData.getUsername().length() <= 6) {
            editPresenter.prepareFailView("Username must be longer than 6 characters.", editOutputData);
            return false;
        }
        if (userService.existsByUsername(editInputData.getUsername()) && !Objects.equals(editInputData.getUsername(), oldUser.getUsername())) {
            editPresenter.prepareFailView("Username already exists.", editOutputData);
            return false;
        }
        if (editInputData.getPassword().length() <= 6) {
            editPresenter.prepareFailView("Password must be longer than 6 characters.", editOutputData);
            return false;
        }
        if (!editInputData.getPassword().equals(editInputData.getRepeatPassword())) {
            editPresenter.prepareFailView("Passwords do not match.", editOutputData);
            return false;
        }
        if (!isValidEmail(editInputData.getEmail())) {
            editPresenter.prepareFailView("Invalid email format.", editOutputData);
            return false;
        }
        if (userService.existsByEmail(editInputData.getEmail()) && !Objects.equals(editInputData.getEmail(), oldUser.getEmail())) {
            editPresenter.prepareFailView("Email already in use.", editOutputData);
            return false;
        }
        return true;
    }

    /**
     * Validates whether the provided email address matches the expected format.
     * @param email Email address to validate.
     * @return true if the email is valid, false otherwise.
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    /**
     * Changes the user's avatar if the selected file is a valid image.
     * Updates the user's profile with the new avatar path after successful validation and upload.
     * @param userId ID of the user whose avatar is to be changed.
     * @param selectedFile File selected by the user as the new avatar.
     */
    @Override
    public void changeAvatar(int userId, File selectedFile) {
        if (!isImageFile(selectedFile)) {
            editPresenter.prepareFailView("File format not supported. Supported formats: jpg, jpeg, png, gif, bmp.", null);
            return;
        }

        fileService.saveAvatar(userId, selectedFile);
        updateUserAvatar(userId, fileService.getOutputFilePath());
    }

    /**
     * Helper method to update the user's avatar.
     * @param userId User ID.
     * @param avatarPath Path to the saved avatar file.
     */
    private void updateUserAvatar(int userId, String avatarPath) {
        User oldUser = SessionManager.getCurrentUser();
        LocalDateTime now = LocalDateTime.now();
        User updatedUser = updateUserFactory.create(oldUser.getId(), oldUser.getUsername(), oldUser.getPassword(),
                oldUser.getEmail(), oldUser.getRole(), avatarPath,
                oldUser.getRegistrationDate(), now);

        editPresenter.present(new EditOutputData(updatedUser.getId(), updatedUser.getUsername(), updatedUser.getPassword(),
                updatedUser.getEmail(), updatedUser.getAvatarUrl()));
        SessionManager.setCurrentUser(updatedUser);
    }

    /**
     * Checks if the provided file is a valid image based on file extension.
     * @param file File to check.
     * @return true if the file is an image, false otherwise.
     */
    private boolean isImageFile(File file) {
        String[] supportedExtensions = {"jpg", "jpeg", "png", "gif", "bmp"};
        String extension = getExtension(file.getName());
        for (String ext : supportedExtensions) {
            if (ext.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Extracts the file extension from a file name.
     * @param fileName The name of the file.
     * @return The file extension.
     */
    private String getExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        return lastIndex > 0 ? fileName.substring(lastIndex + 1) : "";
    }
}
