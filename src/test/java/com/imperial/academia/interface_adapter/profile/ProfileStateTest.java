package com.imperial.academia.interface_adapter.profile;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfileStateTest {
    ProfileState profileState;
    @BeforeEach
    public void init(){
        profileState = new ProfileState();
    }
    @Test
    void getId() {
        int id = profileState.getId();
        assertEquals(-1, id);

    }

    @Test
    void getUsername() {
        String username = profileState.getUsername();
        assertEquals("", username);
    }

    @Test
    void getEmail() {
        String email = profileState.getEmail();
        assertEquals("", email);
    }

    @Test
    void getRole() {
        String role = profileState.getRole();
        assertEquals("", role);
    }

    @Test
    void getAvatarUrl() {
        String url = profileState.getAvatarUrl();
        assertEquals("", url);
    }

    @Test
    void getRegistrationDate() {
        Timestamp registrationDate = profileState.getRegistrationDate();
        assertNotNull(registrationDate);
    }

    @Test
    void isMe() {
        boolean isMe = profileState.isMe();
        assertTrue(isMe);
    }



    @Test
    void getPostTitles() {
        List<String> titles = profileState.getPostTitles();
        assertEquals(new ArrayList<>(), titles);
    }

    @Test
    void getPostContents() {
        List<String> contents = profileState.getPostContents();
        assertEquals(new ArrayList<>(), contents);
    } @Test
    void getPostCreationDates() {
        List<Timestamp> creationDates = profileState.getPostCreationDates();
        assertEquals(new ArrayList<>(), creationDates);
    }

    @Test
    void getPostImageUrls() {
        List<String> urls = profileState.getPostImageUrls();
        assertEquals(new ArrayList<>(), urls);
    }


    @Test
    void getPostIes() {
        List<Integer> ids = profileState.getPostIds();
        assertEquals(new ArrayList<>(), ids);
    }


    @Test
    void setId() {
        profileState.setId(6);
        assertEquals(6, profileState.getId());
    }

    @Test
    void setUsername() {
        profileState.setUsername("aaa");
        assertEquals("aaa", profileState.getUsername());
    }

    @Test
    void setEmail() {
        profileState.setEmail("sss");
        assertEquals("sss", profileState.getEmail());
    }

    @Test
    void setRole() {
        profileState.setRole("sds");
        assertEquals("sds", profileState.getRole());
    }

    @Test
    void setAvatarUrl() {
        profileState.setAvatarUrl("qqq");
        assertEquals("qqq", profileState.getAvatarUrl());
    }

    @Test
    void setRegistrationDate() {
        Timestamp testTimestamp = new Timestamp(2);
        profileState.setRegistrationDate(testTimestamp);
        assertEquals(testTimestamp, profileState.getRegistrationDate());
    }

    @Test
    void setIsMe() {
        profileState.setIsMe(false);
        assertFalse(profileState.isMe());
    }
    @Test
    void constructorTest(){
        profileState.setIsMe(false);
        profileState.setAvatarUrl("ssss");
        ProfileState testProfileState = new ProfileState(profileState);
        assertEquals("ssss", testProfileState.getAvatarUrl());
        assertFalse(testProfileState.isMe());
    }





    @Test
    void setPostTitles() {
        List<String> titles = new ArrayList<String>();
        titles.add("title");
        profileState.setPostTitles(titles);
        assertEquals("title", profileState.getPostTitles().get(0));
    }

    @Test
    void setPostContents() {
        List<String> contents = new ArrayList<String>();
        contents.add("contents");
        profileState.setPostContents(contents);
        assertEquals("contents", profileState.getPostContents().get(0));
    }



    @Test
    void setPostCreationDates() {
        List<Timestamp> creationDates = new ArrayList<Timestamp>();
        creationDates.add(new Timestamp(0));
        profileState.setPostCreationDates(creationDates);
        assertEquals(new Timestamp(0), profileState.getPostCreationDates().get(0));
    }

    @Test
    void setPostImageUrls() {
        List<String> urls = new ArrayList<String>();
        urls.add("url");
        profileState.setPostImageUrls(urls);
        assertEquals("url", profileState.getPostImageUrls().get(0));
    }



    @Test
    void setPostIds() {
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        profileState.setPostIds(ids);
        assertEquals(1, profileState.getPostIds().get(0));
    }
}