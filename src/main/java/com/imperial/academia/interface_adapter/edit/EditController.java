package com.imperial.academia.interface_adapter.edit;

import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.edit.EditInputBoundry;
import com.imperial.academia.use_case.edit.EditInputData;

import java.io.File;

public class EditController {
    private final EditInputBoundry editInteractor;
    private final ChangeViewInputBoundary changeViewInteractor;


    public EditController(){
        this.editInteractor = UsecaseFactory.getEditInteractor();
        this.changeViewInteractor = UsecaseFactory.getChangeViewInteractor();
    }

    public void execute(){
        editInteractor.execute();
    }

    public void update(String username, String password,String repeatPassword, String avatarURL, String email){
        EditInputData editInputData = new EditInputData(username, password, repeatPassword, avatarURL, email);
        editInteractor.update(editInputData);
    }

    public void cancel(){changeViewInteractor.changeView("profile");}

    public void changeAvatar(int userId, File selectedFile){
        editInteractor.changeAvatar(userId, selectedFile);
    }
}
