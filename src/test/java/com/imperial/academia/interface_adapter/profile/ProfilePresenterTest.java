package com.imperial.academia.interface_adapter.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.imperial.academia.use_case.profile.ProfileOutputData;

class ProfilePresenterTest {
    private ProfileViewModel profileViewModel;
    private ProfileOutputData outputData;
    private ProfilePresenter presenter;
    @BeforeEach
    public void init(){
        profileViewModel = new ProfileViewModel();
        presenter = new ProfilePresenter(profileViewModel);

        ArrayList<String> postTitles = new ArrayList<>();
        postTitles.add("title");
        ArrayList<String> postContents = new ArrayList<>();
        postContents.add("contents");
        ArrayList<Timestamp> postCreationDates = new ArrayList<>();
        postCreationDates.add(new Timestamp(0));
        ArrayList<String> postImageUrls = new ArrayList<>();
        postImageUrls.add("url");
        ArrayList<Integer> postIds = new ArrayList<>();
        postIds.add(123);


        outputData = new ProfileOutputData(
                877874918,
                "name",
                "email",
                "role",
                "url",
                new Timestamp(0),
                true,
                postTitles,
                postContents,
                postCreationDates,
                postImageUrls,
                postIds
        );
    }

    @Test
    void present() {
        presenter.present(outputData);
        assertEquals(877874918, profileViewModel.getProfileState().getId());
        assertEquals("name", profileViewModel.getProfileState().getUsername());
        assertEquals("email", profileViewModel.getProfileState().getEmail());
        assertEquals("role", profileViewModel.getProfileState().getRole());
        assertEquals("url", profileViewModel.getProfileState().getAvatarUrl());
        assertEquals(new Timestamp(0), profileViewModel.getProfileState().getRegistrationDate());
        assertTrue(profileViewModel.getProfileState().isMe());


        assertEquals("title", profileViewModel.getProfileState().getPostTitles().get(0));
        assertEquals("contents", profileViewModel.getProfileState().getPostContents().get(0));
        assertEquals(new Timestamp(0), profileViewModel.getProfileState().getPostCreationDates().get(0));
        assertEquals("url", profileViewModel.getProfileState().getPostImageUrls().get(0));
        assertEquals(123, profileViewModel.getProfileState().getPostIds().get(0));


    }

    @Test
    void presentError() {
        presenter.presentError("error");
        assertEquals(-1 , profileViewModel.getProfileState().getId());

        assertEquals("", profileViewModel.getProfileState().getUsername());
        assertEquals("", profileViewModel.getProfileState().getRole());
        assertEquals("", profileViewModel.getProfileState().getUsername());
        assertEquals("", profileViewModel.getProfileState().getAvatarUrl());
        assertNotEquals(new Timestamp(0), profileViewModel.getProfileState().getRegistrationDate());
        assertTrue(profileViewModel.getProfileState().isMe());


        assertEquals(new ArrayList<>(), profileViewModel.getProfileState().getPostTitles());
        assertEquals(new ArrayList<>(), profileViewModel.getProfileState().getPostContents());
        assertEquals(new ArrayList<>(), profileViewModel.getProfileState().getPostCreationDates());
        assertEquals(new ArrayList<>(), profileViewModel.getProfileState().getPostImageUrls());
        assertEquals(new ArrayList<>(), profileViewModel.getProfileState().getPostIds());

    }
}