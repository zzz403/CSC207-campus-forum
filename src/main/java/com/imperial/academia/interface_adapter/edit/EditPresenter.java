package com.imperial.academia.interface_adapter.edit;

import com.imperial.academia.use_case.edit.EditOutputBoundary;
import com.imperial.academia.use_case.edit.EditOutputData;

public class EditPresenter implements EditOutputBoundary {
    private final EditViewModel editViewModel;


    public  EditPresenter(EditViewModel editViewModel){
        this.editViewModel = editViewModel;
    }
    @Override
    public void prepareFailView(String error, EditOutputData editOutputData) {
        EditState editState = editViewModel.getState();
        if(error == null){
            editState.setEmailError(null);
            editState.setPasswordError(null);
            editState.setRepeatPasswordError(null);
            editState.setUsernameError(null);
            editState.setAvatarError(null);
        } else if (error.contains("Username")) { //TODO type
            editState.setUsernameError(error);
        } else if (error.contains("Password")) {
            editState.setPasswordError(error);
        } else if (error.contains("Repeat password")){
            editState.setRepeatPasswordError(error);
        } else if (error.contains("Email")){
            editState.setEmailError(error);
        } else if (error.contains("file")){
            editState.setAvatarError(error);
        }
        editState.setUserName(editOutputData.getUserName());
        editState.setUserId(editOutputData.getUserId());
        editState.setPassword(editOutputData.getPassword());
        editState.setEmail(editOutputData.getEmail());
        editState.setAvatarURL(editOutputData.getAvatarURL());
        editViewModel.setState(editState);
        editViewModel.firePropertyChanged("error");
    }

    @Override
    public void present(EditOutputData editOutputData) {
        EditState editState = editViewModel.getState();
        editState.setUserId(editOutputData.getUserId());
        editState.setUserName(editOutputData.getUserName());
        editState.setPassword(editOutputData.getPassword());
        editState.setEmail(editOutputData.getEmail());
        editState.setAvatarURL(editOutputData.getAvatarURL());

        editViewModel.setState(editState);
        editViewModel.firePropertyChanged("state");
    }
}
