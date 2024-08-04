package com.imperial.academia.interface_adapter.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.imperial.academia.use_case.edit.EditOutputData;

class EditPresenterTest {
    private EditPresenter editPresenter;
    private EditViewModel editViewModel;
    private EditOutputData editOutputData;

    @BeforeEach
    public void init(){
        editViewModel = new EditViewModel();
        editPresenter = new EditPresenter(editViewModel);
        editOutputData = new EditOutputData(
                877874918,
                "name",
                "password",
                "email",
                "url"
        );
        editViewModel.setState(new EditState());
    }
    @Test
    void prepareFailViewNull() {
        editPresenter.prepareFailView(null,editOutputData);
        assertNull(editViewModel.getState().getEmailError());
        assertNull(editViewModel.getState().getPasswordError());
        assertNull(editViewModel.getState().getRepeatPasswordError());
        assertNull(editViewModel.getState().getUsernameError());
        assertNull(editViewModel.getState().getAvatarError());
        assertEquals("name", editViewModel.getState().getUserName());
        assertEquals("password", editViewModel.getState().getPassword());
        assertEquals(877874918, editViewModel.getState().getUserId());
        assertEquals("email", editViewModel.getState().getEmail());
        assertEquals("url", editViewModel.getState().getAvatarURL());
    }


    @Test
    void prepareFailViewUserName() {
        editPresenter.prepareFailView("Username",editOutputData);
        assertNull(editViewModel.getState().getEmailError());
        assertNull(editViewModel.getState().getPasswordError());
        assertNull(editViewModel.getState().getRepeatPasswordError());
        assertNull(editViewModel.getState().getAvatarError());
        assertEquals("Username", editViewModel.getState().getUsernameError());
        assertEquals("name", editViewModel.getState().getUserName());
        assertEquals("password", editViewModel.getState().getPassword());
        assertEquals(877874918, editViewModel.getState().getUserId());
        assertEquals("email", editViewModel.getState().getEmail());
        assertEquals("url", editViewModel.getState().getAvatarURL());
    }

    @Test
    void prepareFailViewPassword() {
        editPresenter.prepareFailView("Password",editOutputData);
        assertNull(editViewModel.getState().getEmailError());
        assertNull(editViewModel.getState().getRepeatPasswordError());
        assertNull(editViewModel.getState().getUsernameError());
        assertNull(editViewModel.getState().getAvatarError());
        assertEquals("Password", editViewModel.getState().getPasswordError());
        assertEquals("name", editViewModel.getState().getUserName());
        assertEquals("password", editViewModel.getState().getPassword());
        assertEquals(877874918, editViewModel.getState().getUserId());
        assertEquals("email", editViewModel.getState().getEmail());
        assertEquals("url", editViewModel.getState().getAvatarURL());
    }

    @Test
    void prepareFailViewRepeatPassword() {
        editPresenter.prepareFailView("Repeat password",editOutputData);
        assertNull(editViewModel.getState().getEmailError());
        assertNull(editViewModel.getState().getPasswordError());
        assertEquals("Repeat password", editViewModel.getState().getRepeatPasswordError());
        assertNull(editViewModel.getState().getUsernameError());
        assertNull(editViewModel.getState().getAvatarError());
        assertEquals("name", editViewModel.getState().getUserName());
        assertEquals("password", editViewModel.getState().getPassword());
        assertEquals(877874918, editViewModel.getState().getUserId());
        assertEquals("email", editViewModel.getState().getEmail());
        assertEquals("url", editViewModel.getState().getAvatarURL());
    }


    @Test
    void prepareFailViewEmail() {
        editPresenter.prepareFailView("Email",editOutputData);
        assertEquals("Email", editViewModel.getState().getEmailError());
        assertNull(editViewModel.getState().getPasswordError());
        assertNull(editViewModel.getState().getRepeatPasswordError());
        assertNull(editViewModel.getState().getUsernameError());
        assertNull(editViewModel.getState().getAvatarError());
        assertEquals("name", editViewModel.getState().getUserName());
        assertEquals("password", editViewModel.getState().getPassword());
        assertEquals(877874918, editViewModel.getState().getUserId());
        assertEquals("email", editViewModel.getState().getEmail());
        assertEquals("url", editViewModel.getState().getAvatarURL());
    }


    @Test
    void prepareFailViewAvatar() {
        editPresenter.prepareFailView("file",editOutputData);
        assertNull(editViewModel.getState().getEmailError());
        assertNull(editViewModel.getState().getPasswordError());
        assertNull(editViewModel.getState().getRepeatPasswordError());
        assertNull(editViewModel.getState().getUsernameError());
        assertEquals("file",editViewModel.getState().getAvatarError() );
        assertEquals("name", editViewModel.getState().getUserName());
        assertEquals("password", editViewModel.getState().getPassword());
        assertEquals(877874918, editViewModel.getState().getUserId());
        assertEquals("email", editViewModel.getState().getEmail());
        assertEquals("url", editViewModel.getState().getAvatarURL());
    }


    @Test
    void present() {
        editPresenter.present(editOutputData);
        assertNull(editViewModel.getState().getEmailError());
        assertNull(editViewModel.getState().getPasswordError());
        assertNull(editViewModel.getState().getRepeatPasswordError());
        assertNull(editViewModel.getState().getUsernameError());
        assertNull(editViewModel.getState().getAvatarError());
        assertEquals("name", editViewModel.getState().getUserName());
        assertEquals("password", editViewModel.getState().getPassword());
        assertEquals(877874918, editViewModel.getState().getUserId());
        assertEquals("email", editViewModel.getState().getEmail());
        assertEquals("url", editViewModel.getState().getAvatarURL());
    }
    @Test
    void prepareFailViewWrongMessage() {
        editPresenter.prepareFailView("abc",editOutputData);
        assertNull(editViewModel.getState().getEmailError());
        assertNull(editViewModel.getState().getPasswordError());
        assertNull(editViewModel.getState().getRepeatPasswordError());
        assertNull(editViewModel.getState().getUsernameError());
        assertNull(editViewModel.getState().getAvatarError());
        assertEquals("name", editViewModel.getState().getUserName());
        assertEquals("password", editViewModel.getState().getPassword());
        assertEquals(877874918, editViewModel.getState().getUserId());
        assertEquals("email", editViewModel.getState().getEmail());
        assertEquals("url", editViewModel.getState().getAvatarURL());
    }
}