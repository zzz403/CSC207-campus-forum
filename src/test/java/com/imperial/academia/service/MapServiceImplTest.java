package com.imperial.academia.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockStatic;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import com.imperial.academia.config.ApiKeyConfig;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.Buffer;

public class MapServiceImplTest {

    private MockWebServer mockWebServer;
    private MapServiceImpl mapService;
    private MockedStatic<ApiKeyConfig> mockedApiKeyConfig;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        mockedApiKeyConfig = mockStatic(ApiKeyConfig.class);
        mockedApiKeyConfig.when(ApiKeyConfig::getMapBoxApiKey).thenReturn("test-api-key");
        mapService = new MapServiceImpl();
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
        mockedApiKeyConfig.close();
    }

    @Test
    void testGetUserLocation() throws IOException {
        String mockResponse = "{\"lat\":37.7749,\"lon\":-122.4194}";
        mockWebServer.enqueue(new MockResponse().setBody(mockResponse).addHeader("Content-Type", "application/json"));

        double[] location = mapService.getUserLocation();

        assertNotNull(location, "The location should not be null");
    }


    private Buffer imageToBuffer(BufferedImage image) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "png", os);
        Buffer buffer = new Buffer();
        buffer.write(os.toByteArray());
        return buffer;
    }
}
