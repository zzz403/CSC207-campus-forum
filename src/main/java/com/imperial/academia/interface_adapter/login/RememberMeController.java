package com.imperial.academia.interface_adapter.login;

import com.imperial.academia.use_case.login.RememberMeUseCase;

import java.io.IOException;

public class RememberMeController {
    private final RememberMeUseCase rememberMeUseCase;

    public RememberMeController(RememberMeUseCase rememberMeUseCase) {
        this.rememberMeUseCase = rememberMeUseCase;
    }

    public void saveCredentials(String username, String password) {
        try {
            rememberMeUseCase.saveCredentials(username, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] loadCredentials() {
        try {
            return rememberMeUseCase.loadCredentials();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[2];
    }

    public void clearCredentials() {
        try {
            rememberMeUseCase.clearCredentials();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
