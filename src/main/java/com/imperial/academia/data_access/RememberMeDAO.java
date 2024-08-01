package com.imperial.academia.data_access;

import java.io.*;

public class RememberMeDAO implements RememberMeDAI {
    private static final String DEFAULT_FILE_PATH = "database/rememberme.txt";
    private String filePath;

    public RememberMeDAO() {
        this.filePath = DEFAULT_FILE_PATH;
    }

    public RememberMeDAO(String filePath) {
        this.filePath = filePath;
    }

    protected String getFilePath() {
        return filePath;
    }

    @Override
    public void saveCredentials(String username, String password) throws IOException {
        try (FileWriter writer = new FileWriter(getFilePath())) {
            writer.write(username + "\n");
            writer.write(password + "\n");
        }
    }

    @Override
    public String[] loadCredentials() throws IOException {
        String[] credentials = new String[2];
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
            credentials[0] = reader.readLine();
            credentials[1] = reader.readLine();
        }
        return credentials;
    }

    @Override
    public void clearCredentials() throws IOException {
        try (FileWriter writer = new FileWriter(getFilePath())) {
            writer.write("");
        }
    }
}
