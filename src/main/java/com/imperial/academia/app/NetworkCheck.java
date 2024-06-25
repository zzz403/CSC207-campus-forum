package com.imperial.academia.app;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkCheck {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://i.pravatar.cc/150?img=1");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int code = connection.getResponseCode();
            if (code == 200) {
                System.out.println("Successfully connected to " + url);
            } else {
                System.out.println("Failed to connect to " + url + " with response code " + code);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
