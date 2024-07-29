package com.imperial.academia.use_case.edit;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.entity.user.UpdateUserFactory;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.entity.user.UserFactory;
import com.imperial.academia.service.FileService;
import com.imperial.academia.service.UserService;
import com.imperial.academia.session.SessionManager;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.chat.ChatWindowInputData;
import com.imperial.academia.use_case.profile.ProfileInputData;
import org.jetbrains.annotations.NotNull;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.regex.Pattern;

import com.imperial.academia.service.FileService;


public class EditInteractor implements EditInputBoundry {
    private final ChangeViewInputBoundary changeViewInteractor;
    private final EditOutputBoundary editPresenter;
    private final UpdateUserFactory updateUserFactory;
    private final UserService userService;
    private final FileService fileService;


    public EditInteractor(EditOutputBoundary editPresenter, UpdateUserFactory updateUserFactory){
        this.editPresenter = editPresenter;
        this.userService = ServiceFactory.getUserService();
        this.updateUserFactory = updateUserFactory;
        this.changeViewInteractor = UsecaseFactory.getChangeViewInteractor();
        this.fileService = ServiceFactory.getFileService();

    }

    public void execute(){
        User user = SessionManager.getCurrentUser();
        if (user == null){
            changeViewInteractor.changeView("login");
        } else {
            EditOutputData editOutputData = new EditOutputData(
                    user.getId(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getAvatarUrl()
            );
            editPresenter.present(editOutputData);
            changeViewInteractor.changeView("edit");
        }
    }

    public void update(@NotNull EditInputData editInputData){
        User oldUser = SessionManager.getCurrentUser();



        if (oldUser == null){
            changeViewInteractor.changeView("login");
        }else{
            LocalDateTime now = LocalDateTime.now();
            User updatedUser = updateUserFactory.create(
                    oldUser.getId(),
                    editInputData.getUsername(),
                    editInputData.getPassword(),
                    editInputData.getEmail(),
                    oldUser.getRole(),
                    oldUser.getAvatarUrl(),
                    oldUser.getRegistrationDate(),
                    now
            );
            EditOutputData editOutputData = new EditOutputData(
                    updatedUser.getId(),
                    updatedUser.getUsername(),
                    updatedUser.getPassword(),
                    updatedUser.getEmail(),
                    updatedUser.getAvatarUrl()
            );


            try {
                editPresenter.prepareFailView(null,editOutputData);
                if (editInputData.getUsername().length() <= 6) {
                    editPresenter.prepareFailView("Username must be longer than 6 characters.", editOutputData);
                } else if (userService.existsByUsername(editInputData.getUsername()) &&
                        !Objects.equals(editInputData.getUsername(), oldUser.getUsername())) {
                    editPresenter.prepareFailView("User already exists.", editOutputData);
                } else if (editInputData.getPassword().length() <= 6) {
                    editPresenter.prepareFailView("Password must be longer than 6 characters.", editOutputData);
                } else if (!editInputData.getPassword().equals(editInputData.getRepeatPassword())) {
                    editPresenter.prepareFailView("Repeat password don't match.", editOutputData);
                } else if (!isValidEmail(editInputData.getEmail())) {
                    editPresenter.prepareFailView("Email is not valid.", editOutputData);
                } else if (userService.existsByEmail(editInputData.getEmail()) &&
                        !Objects.equals(editInputData.getEmail(), oldUser.getEmail())) {
                    editPresenter.prepareFailView("Email already used.", editOutputData);
                } else {
                    System.out.println("123123");
                    SessionManager.setCurrentUser(updatedUser);
                    userService.update(updatedUser);
                    ProfileInputData profileInputData = new ProfileInputData(updatedUser.getId());
                    UsecaseFactory.getProfileInteractor().execute(profileInputData);
                }
            } catch (SQLException e) {
                editPresenter.prepareFailView("An error occurred during signup: " + e.getMessage(), editOutputData);
            }
        }

    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    private boolean isImageFile(File file) {
        String fileName = file.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("gif")|| extension.equals("bmp");
    }

    public void changeAvatar(int userId, File selectedFile){

        User oldUser = SessionManager.getCurrentUser();
        LocalDateTime now = LocalDateTime.now();
        if (oldUser == null){
            changeViewInteractor.changeView("login");
        }else{
            if (isImageFile(selectedFile)) {
                fileService.saveAvatar(userId, selectedFile);
                String filePath = fileService.getOutputFilePath();
                User updatedUser = updateUserFactory.create(
                        oldUser.getId(),
                        oldUser.getUsername(),
                        oldUser.getPassword(),
                        oldUser.getEmail(),
                        oldUser.getRole(),
                        filePath,
                        oldUser.getRegistrationDate(),
                        now
                );
                EditOutputData editOutputData = new EditOutputData(
                        updatedUser.getId(),
                        updatedUser.getUsername(),
                        updatedUser.getPassword(),
                        updatedUser.getEmail(),
                        updatedUser.getAvatarUrl()
                );
                editPresenter.present(editOutputData);
                SessionManager.setCurrentUser(updatedUser);
            }else{
                User updatedUser = updateUserFactory.create(
                        oldUser.getId(),
                        oldUser.getUsername(),
                        oldUser.getPassword(),
                        oldUser.getEmail(),
                        oldUser.getRole(),
                        oldUser.getAvatarUrl(),
                        oldUser.getRegistrationDate(),
                        now
                );
                EditOutputData editOutputData = new EditOutputData(
                        updatedUser.getId(),
                        updatedUser.getUsername(),
                        updatedUser.getPassword(),
                        updatedUser.getEmail(),
                        updatedUser.getAvatarUrl()
                );
                editPresenter.prepareFailView("Uploaded file is not in correct format", editOutputData);



        }













        }
    }
}
