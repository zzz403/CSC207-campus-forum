package com.imperial.academia.data_access;

import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class RememberMeDAOTest {
    private RememberMeDAO rememberMeDAO;
    private final String testFilePath = "database/test_rememberme.txt";

    @BeforeEach
    void setUp() {
        rememberMeDAO = new RememberMeDAO(testFilePath);
    }

    @AfterEach
    void tearDown() throws IOException {
        File file = new File(testFilePath);
        if (file.exists()) {
            assertTrue(file.delete());
        }
    }

    @Test
    void saveCredentials_shouldSaveUsernameAndPassword() throws IOException {
        String username = "testUser";
        String password = "testPassword";

        rememberMeDAO.saveCredentials(username, password);

        File file = new File(testFilePath);
        assertTrue(file.exists());

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            assertEquals(username, reader.readLine());
            assertEquals(password, reader.readLine());
        }
    }

    @Test
    void loadCredentials_shouldLoadUsernameAndPassword() throws IOException {
        String username = "testUser";
        String password = "testPassword";

        try (FileWriter writer = new FileWriter(testFilePath)) {
            writer.write(username + "\n");
            writer.write(password + "\n");
        }

        String[] credentials = rememberMeDAO.loadCredentials();

        assertNotNull(credentials);
        assertEquals(2, credentials.length);
        assertEquals(username, credentials[0]);
        assertEquals(password, credentials[1]);
    }

    @Test
    void clearCredentials_shouldClearTheFile() throws IOException {
        try (FileWriter writer = new FileWriter(testFilePath)) {
            writer.write("someUsername\n");
            writer.write("somePassword\n");
        }

        rememberMeDAO.clearCredentials();

        File file = new File(testFilePath);
        assertTrue(file.exists());

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            assertNull(reader.readLine());
        }
    }

    @Test
    void saveCredentials_shouldThrowIOException_whenFileCannotBeWritten() {
        RememberMeDAO daoWithInvalidPath = new RememberMeDAO("/invalid/path/rememberme.txt");

        assertThrows(IOException.class, () -> {
            daoWithInvalidPath.saveCredentials("testUser", "testPassword");
        });
    }

    @Test
    void loadCredentials_shouldThrowIOException_whenFileCannotBeRead() {
        RememberMeDAO daoWithInvalidPath = new RememberMeDAO("/invalid/path/rememberme.txt");

        assertThrows(IOException.class, () -> {
            daoWithInvalidPath.loadCredentials();
        });
    }
}
