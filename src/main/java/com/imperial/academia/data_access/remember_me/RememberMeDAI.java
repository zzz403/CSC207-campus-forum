package com.imperial.academia.data_access.remember_me;

import java.io.IOException;

public interface RememberMeDAI {
    void saveCredentials(String username, String password) throws IOException;

    String[] loadCredentials() throws IOException;

    void clearCredentials() throws IOException;
}
