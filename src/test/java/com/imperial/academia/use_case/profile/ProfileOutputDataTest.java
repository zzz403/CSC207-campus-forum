package com.imperial.academia.use_case.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProfileOutputDataTest {
    private int id;

    private String username;


    private String email;


    private String role;

    private String avatarUrl;

    private Timestamp registrationDate;

    private boolean isMe;
    private ProfileOutputData outputData;

    private List<String> postTitles;
    private List<String> postContents;
    private List<Timestamp> postCreationDates;
    private List<String> postImageUrls;
    private List<Integer> postIds;

    @BeforeEach
    public void init(){
        id = 1;
        username = "name";
        email = "email";
        role = "role";
        avatarUrl = "url";
        registrationDate = new Timestamp(1);
        isMe = true;
        postTitles = new ArrayList<String>();
        postContents = new ArrayList<String>();
        postCreationDates= new ArrayList<Timestamp>();
        postImageUrls = new ArrayList<String>();
        postIds = new ArrayList<Integer>();

        outputData = new ProfileOutputData(id,username,email,role,avatarUrl,registrationDate,isMe,
                postTitles,postContents,postCreationDates, postImageUrls,postIds);

    }

    @Test
    void getId() {
        assertEquals(id, outputData.getId());
    }

    @Test
    void getRegistrationDate() {
        assertEquals(registrationDate, outputData.getRegistrationDate());
    }

    @Test
    void getAvatarUrl() {
        assertEquals(avatarUrl, outputData.getAvatarUrl());
    }

    @Test
    void getRole() {
        assertEquals(role, outputData.getRole());
    }

    @Test
    void getEmail() {
        assertEquals(email, outputData.getEmail());
    }

    @Test
    void getUsername() {
        assertEquals(username, outputData.getUsername());
    }

    @Test
    void isMe() {
        assertEquals(isMe, outputData.isMe());
    }

   @Test
    void PostTiles(){
        assertEquals(postTitles, outputData.getPostTitles());
   }


    @Test
    void PostContents(){
        assertEquals(postContents, outputData.getPostContents());
    }

    @Test
    void PostCreationDates(){
        assertEquals(postCreationDates, outputData.getPostCreationDates());
    }


    @Test
    void PostImageUrls(){
        assertEquals(postImageUrls, outputData.getPostImageUrls());
    }

    @Test
    void PostIds(){
        assertEquals(postIds, outputData.getPostIds());
    }

}