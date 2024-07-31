package com.imperial.academia.interface_adapter.edit;

import com.imperial.academia.interface_adapter.profile.ProfileState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditStateTest {
    private EditState editState;

    @BeforeEach
    public void init(){editState = new EditState();};

    @Test
    void getAvatarError() {
        String error = editState.getAvatarError();
        assertNull(error);
    }

    @Test
    void setAvatarError() {
        editState.setAvatarError("abc");
        assertEquals("abc", editState.getAvatarError());
    }

    @Test
    void getAvatarURL() {
    assertEquals("", editState.getAvatarURL());
    }

    @Test
    void getUserId() {
        assertEquals(0,editState.getUserId());
    }

    @Test
    void setUserId() {
        editState.setUserId(2);
        assertEquals(2,editState.getUserId());
    }

    @Test
    void setAvatarURL() {
        editState.setAvatarURL("123");
        assertEquals("123", editState.getAvatarURL());
    }

    @Test
    void getUserName() {
        assertEquals("", editState.getUserName());
    }

    @Test
    void getUsernameError() {
        assertNull(editState.getUsernameError());
    }

    @Test
    void getPassword() {
        assertEquals("", editState.getPassword());
    }

    @Test
    void getPasswordError() {
        assertNull(editState.getPasswordError());
    }

    @Test
    void getRepeatPassword() {
        assertEquals("",editState.getRepeatPassword());
    }

    @Test
    void getRepeatPasswordError() {
        assertNull(editState.getRepeatPasswordError());
    }

    @Test
    void getEmail() {
        assertEquals("",editState.getEmail());
    }

    @Test
    void getEmailError() {
        assertNull(editState.getEmailError());
    }

    @Test
    void setUserName() {
        editState.setUserName("qwe");
        assertEquals("qwe",editState.getUserName());
    }

    @Test
    void setUsernameError() {
        editState.setUsernameError("qwe");
        assertEquals("qwe", editState.getUsernameError());
    }

    @Test
    void setPassword() {
        editState.setPassword("qwe");
        assertEquals("qwe",editState.getPassword());
    }

    @Test
    void setPasswordError() {
        editState.setPasswordError("asd");
        assertEquals("asd", editState.getPasswordError());
    }

    @Test
    void setRepeatPassword() {
        editState.setRepeatPassword("fff");
        assertEquals("fff", editState.getRepeatPassword());
    }

    @Test
    void setRepeatPasswordError() {
        editState.setRepeatPasswordError("fff");
        assertEquals("fff", editState.getRepeatPasswordError());
    }

    @Test
    void setEmail() {
        editState.setEmail("fff");
        assertEquals("fff", editState.getEmail());
    }

    @Test
    void setEmailError() {
        editState.setEmailError("fff");
        assertEquals("fff", editState.getEmailError());
    }

    @Test
    void clear() {
        editState.setEmail("123sda");
        editState.clear();
        assertEquals("",editState.getEmail());
    }

    @Test
    void clearErrors() {
        editState.setEmailError("123sda");
        editState.clearErrors();
        assertEquals(null,editState.getEmailError());
    }

    @Test
    void constructorTest(){
        editState.setEmail("asdasd");
        editState.setUserName("ssss");
        EditState testEditState = new EditState(editState);
        assertEquals("ssss", testEditState.getUserName());
        assertEquals("asdasd", testEditState.getEmail());

    }
}