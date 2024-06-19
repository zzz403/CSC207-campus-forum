package com.imperial.academia.data_access;

import java.io.*;

public class RememberMeDAO {
    private static final String FILE_PATH = "src/main/java/com/imperial/academia/database/rememberme.txt";

    public void saveCredentials(String username, String password) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(username + "\n");
            writer.write(password + "\n");
        }
    }

    public String[] loadCredentials() throws IOException {
        String[] credentials = new String[2];
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            credentials[0] = reader.readLine();
            credentials[1] = reader.readLine();
        }
        return credentials;
    }

    public void clearCredentials() throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write("");
        }
    }
}
