package com.imperial.academia.interface_adapter.edit;

import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.use_case.edit.EditInputBoundry;
import com.imperial.academia.use_case.edit.EditInputData;

public class EditController {
    private final EditInputBoundry editInteractor;



    public EditController(){
        this.editInteractor = UsecaseFactory.getEditInteractor();
    }

    public void execute(){
        editInteractor.execute();
    }

    public void update(String username, String password,String repeatPassword, String avatarURL, String email){
        EditInputData editInputData = new EditInputData(username, password, repeatPassword, avatarURL, email);
        editInteractor.update(editInputData);
    }
}
