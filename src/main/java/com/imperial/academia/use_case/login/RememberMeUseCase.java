package com.imperial.academia.use_case.login;

import com.imperial.academia.data_access.RememberMeDAO;

import java.io.IOException;

public class RememberMeUseCase {
    private final RememberMeDAO rememberMeDAO;

    public RememberMeUseCase(RememberMeDAO rememberMeDAO) {
        this.rememberMeDAO = rememberMeDAO;
    }

    public void saveCredentials(String username, String password) throws IOException {
        rememberMeDAO.saveCredentials(username, password);
    }

    public String[] loadCredentials() throws IOException {
        return rememberMeDAO.loadCredentials();
    }

    public void clearCredentials() throws IOException {
        rememberMeDAO.clearCredentials();
    }
}
