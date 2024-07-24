package com.imperial.academia.interface_adapter.profile;

import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.session.SessionManager;
import com.imperial.academia.use_case.chat.ChatCoordinatorInputBoundary;
import com.imperial.academia.use_case.profile.ProfileInputBoundry;
import com.imperial.academia.use_case.profile.ProfileInputData;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ProfileControllerTest {
    private ProfileInputBoundry mockProfileInteractor;
    private ChatCoordinatorInputBoundary mockChatCoordinatorInteractor;
    private ProfileInputData profileInputDataOut;
    private int userIdOut;
    private ProfileController profileController;
    @BeforeEach
    public void init(){
        mockProfileInteractor = new ProfileInputBoundry() {
            @Override
            public void execute(ProfileInputData profileInputData) {
                profileInputDataOut = profileInputData;
            }
        };

        mockChatCoordinatorInteractor = new ChatCoordinatorInputBoundary() {
            @Override
            public void toPrivateChat(int userId) {
                userIdOut = userId;
            }
        };
        try(MockedStatic<UsecaseFactory> mockedStatic = Mockito.mockStatic(UsecaseFactory.class)){
            mockedStatic.when(UsecaseFactory::getProfileInteractor).thenReturn(mockProfileInteractor);
            mockedStatic.when(UsecaseFactory::getChatCoordinatorInteractor).thenReturn(mockChatCoordinatorInteractor);
            profileController = new ProfileController();

        }
    }
    @Test
    void showProfile() {
        profileController.showProfile(1);
        assertEquals(1, profileInputDataOut.getUserId());
    }

    @Test
    void chat() {
        profileController.chat(2);
        assertEquals(2, userIdOut);
    }

    @Test
    void edit() {
        //TODO
        profileController.edit();
    }
}