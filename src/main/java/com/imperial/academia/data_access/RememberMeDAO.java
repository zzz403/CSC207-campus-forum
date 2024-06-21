package com.imperial.academia.data_access;

import java.io.*;

/**
 * RememberMeDAO is an implementation of the RememberMeDAI interface.
 * It provides methods to save, load, and clear user credentials using a file.
 */
public class RememberMeDAO implements RememberMeDAI {
    private static final String FILE_PATH = "database/rememberme.txt";

    /**
     * Saves the provided username and password to a file.
     * 
     * @param username The username to be saved.
     * @param password The password to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    @Override
    public void saveCredentials(String username, String password) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(username + "\n");
            writer.write(password + "\n");
        }
    }

    /**
     * Loads the saved username and password from a file.
     * 
     * @return An array containing the username and password.
     * @throws IOException If an I/O error occurs while reading from the file.
     */
    @Override
    public String[] loadCredentials() throws IOException {
        String[] credentials = new String[2];
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            credentials[0] = reader.readLine();
            credentials[1] = reader.readLine();
        }
        return credentials;
    }

    /**
     * Clears the saved credentials in the file.
     * 
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    @Override
    public void clearCredentials() throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write("");
        }
    }
}
