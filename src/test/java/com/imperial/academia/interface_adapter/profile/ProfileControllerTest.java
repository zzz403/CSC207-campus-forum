package com.imperial.academia.interface_adapter.profile;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.chat.ChatCoordinatorInputBoundary;
import com.imperial.academia.use_case.edit.EditInputBoundary;
import com.imperial.academia.use_case.post.PostInputBoundary;
import com.imperial.academia.use_case.profile.ProfileInputBoundary;
import com.imperial.academia.use_case.profile.ProfileInputData;

@SuppressWarnings("unused")
class ProfileControllerTest {
    private ProfileInputBoundary mockProfileInteractor;
    private ChatCoordinatorInputBoundary mockChatCoordinatorInteractor;
    private EditInputBoundary mockEditInteractor;
    private ChangeViewInputBoundary mockChangeViewInteractor;
    private  PostInputBoundary mockPostInteractor;
    private ProfileInputData profileInputDataOut;
    private int userIdOut;
    private ProfileController profileController;

    @BeforeEach
    public void init() {
        mockProfileInteractor = mock(ProfileInputBoundary.class);
        mockChatCoordinatorInteractor = mock(ChatCoordinatorInputBoundary.class);
        mockEditInteractor = mock(EditInputBoundary.class);
        mockChangeViewInteractor = mock(ChangeViewInputBoundary.class);
        mockPostInteractor = mock(PostInputBoundary.class);

        try (MockedStatic<UsecaseFactory> mockedStatic = Mockito.mockStatic(UsecaseFactory.class)) {
            mockedStatic.when(UsecaseFactory::getProfileInteractor).thenReturn(mockProfileInteractor);
            mockedStatic.when(UsecaseFactory::getChatCoordinatorInteractor).thenReturn(mockChatCoordinatorInteractor);
            mockedStatic.when(UsecaseFactory::getEditInteractor).thenReturn(mockEditInteractor);
            mockedStatic.when(UsecaseFactory::getChangeViewInteractor).thenReturn(mockChangeViewInteractor);
            mockedStatic.when(UsecaseFactory::getPostInteractor).thenReturn(mockPostInteractor);

            profileController = new ProfileController();
        }
    }

    @Test
    void showProfile() {
        profileController.showProfile(1);
        verify(mockProfileInteractor).execute(any(ProfileInputData.class));
    }

    @Test
    void chat() {
        profileController.chat(2);
        verify(mockChatCoordinatorInteractor).toPrivateChat(2);
    }

    @Test
    void edit() {
        profileController.edit();
        verify(mockEditInteractor).execute();
    }
    @Test
    void initPostIdTrue(){
        when(mockPostInteractor.initPostById(2)).thenReturn(true);

        boolean result = profileController.initPostById(2);
        assertTrue(result);
        verify(mockChangeViewInteractor).changeView("post");
    }

    @Test
    void initPostIdFalse(){
        when(mockPostInteractor.initPostById(1)).thenReturn(false);

        boolean result = profileController.initPostById(1);
        assertFalse(result);
        verify(mockChangeViewInteractor, never()).changeView("post");
    }


}
