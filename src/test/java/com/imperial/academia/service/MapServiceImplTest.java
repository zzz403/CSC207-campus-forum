package com.imperial.academia.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import com.imperial.academia.config.ApiKeyConfig;

import okhttp3.OkHttpClient;
import okhttp3.Response;

public class MapServiceImplTest {
    @Mock
    private OkHttpClient client;
    @Mock
    private Response response;

    @InjectMocks
    private MapServiceImpl mapService;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private AutoCloseable closeable;

    @Before
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        closeable.close();
    }


    @Test
    public void testGetUserLocation() throws Exception {
        // Mocking URL connection
        String mockResponse = "{\"lat\": 37.7749, \"lon\": -122.4194}";
        HttpURLConnection connection = mock(HttpURLConnection.class);
        InputStream inputStream = new ByteArrayInputStream(mockResponse.getBytes());
        when(connection.getInputStream()).thenReturn(inputStream);
        when(connection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);

        URL url = mock(URL.class);
        when(url.openConnection()).thenReturn(connection);

        // Mock URL instantiation
        try (MockedConstruction<URL> mockedURL = mockConstruction(URL.class, (mock, context) -> {
            when(mock.openConnection()).thenReturn(connection);
        })) {
            // Get user location
            double[] location = mapService.getUserLocation();

            // Validate location
            assertEquals(37.7749, location[0], 0.0001);
            assertEquals(-122.4194, location[1], 0.0001);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsExceptionWhenApiKeyIsMissing() {
        // Mocking API key
        try (MockedStatic<ApiKeyConfig> mockedApiKeyConfig = mockStatic(ApiKeyConfig.class)) {
            mockedApiKeyConfig.when(ApiKeyConfig::getMapBoxApiKey).thenReturn("");

            new MapServiceImpl();
        }
    }
}
