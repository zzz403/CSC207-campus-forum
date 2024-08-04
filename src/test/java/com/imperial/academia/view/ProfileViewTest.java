package com.imperial.academia.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.imperial.academia.interface_adapter.profile.ProfileState;
import com.imperial.academia.interface_adapter.profile.ProfileViewModel;

class ProfileViewTest {
    private ProfileView profileView;
    private ProfileViewModel mockProfileViewModel;

    @BeforeEach
    public void init(){
        mockProfileViewModel = mock(ProfileViewModel.class);
        ProfileState mockProfileState = new ProfileState();

        ArrayList<String> titles = new ArrayList<>();
        titles.add("title");


        mockProfileState.setPostTitles(new ArrayList<>());
        mockProfileState.setPostContents(new ArrayList<>());
        mockProfileState.setPostCreationDates(new ArrayList<>());
        mockProfileState.setPostImageUrls(new ArrayList<>());
        mockProfileState.setPostIds(new ArrayList<>());

        when(mockProfileViewModel.getProfileState()).thenReturn(mockProfileState);

        profileView = new ProfileView(mockProfileViewModel);
    }


    @Test
    void viewName(){
        when(mockProfileViewModel.getProfileState()).thenReturn(new ProfileState());
        assertEquals("profile", profileView.viewName);
    }
}