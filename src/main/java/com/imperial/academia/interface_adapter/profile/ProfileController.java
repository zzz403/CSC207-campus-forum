package com.imperial.academia.interface_adapter.profile;

import com.imperial.academia.use_case.chat.ChatCoordinatorInputBoundary;
import com.imperial.academia.use_case.profile.ProfileInputBoundry;
import com.imperial.academia.use_case.profile.ProfileInputData;

public class ProfileController{
    private final ProfileInputBoundry profileInteractor;
    private final ChatCoordinatorInputBoundary chatCoordinatorInteractor;

    public ProfileController(ProfileInputBoundry profileInteractor, ChatCoordinatorInputBoundary chatCoordinatorInteractor) {
        this.profileInteractor = profileInteractor;
        this.chatCoordinatorInteractor = chatCoordinatorInteractor;
    }


    public void showProfile(int userId){
        ProfileInputData profileInputData = new ProfileInputData(userId);
        profileInteractor.excute(profileInputData);
    }

    public void chat(int userId){
        chatCoordinatorInteractor.toPrivateChat(userId);
    }

    public void edit(){
        //TODO  edit need to be done
    }
}
