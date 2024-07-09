package com.imperial.academia.interface_adapter.profile;

import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.chat.ChatSideBarInputBoundary;
import com.imperial.academia.use_case.profile.ProfileInputBoundry;
import com.imperial.academia.use_case.profile.ProfileInputData;

public class ProfileController{
    private final ProfileInputBoundry profileInteractor;
    private final ChatSideBarInputBoundary chatSideBarIteractor;

    public ProfileController(ProfileInputBoundry profileInteractor, ChatSideBarInputBoundary chatSideBarIterator) {
        this.profileInteractor = profileInteractor;
        this.chatSideBarIteractor = chatSideBarIterator;
    }


    public void showProfile(int userId){
        ProfileInputData profileInputData = new ProfileInputData(userId);
        profileInteractor.excute(profileInputData);
    }

    public void chat(int userId){
//        chatSideBarIteractor.chatWith(userId);
        // TODO chatWith need to be done
    }
}
