package com.imperial.academia.data_access;

import java.io.IOException;

/**
 * RememberMeDAI is an interface for managing 'Remember Me' functionality.
 * It provides methods to save, load, and clear user credentials.
 */
public interface RememberMeDAI {
    /**
     * Saves the provided username and password.
     * 
     * @param username The username to be saved.
     * @param password The password to be saved.
     * @throws IOException If an I/O error occurs.
     */
    void saveCredentials(String username, String password) throws IOException;

    /**
     * Loads the saved username and password.
     * 
     * @return An array containing the username and password.
     * @throws IOException If an I/O error occurs.
     */
    String[] loadCredentials() throws IOException;

    /**
     * Clears the saved credentials.
     * 
     * @throws IOException If an I/O error occurs.
     */
    void clearCredentials() throws IOException;
}
