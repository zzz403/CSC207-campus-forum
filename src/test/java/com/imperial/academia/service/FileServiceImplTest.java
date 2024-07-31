package com.imperial.academia.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.imperial.academia.entity.chat_message.FileData;

public class FileServiceImplTest {
    private FileServiceImpl fileService;
    private Path tempDirectory;

    @Before
    public void setUp() throws IOException {
        fileService = new FileServiceImpl();
        tempDirectory = Files.createTempDirectory("testDir");
    }

    @After
    public void tearDown() throws IOException {
        // Clean up temporary directory
        Files.walk(tempDirectory)
                .map(Path::toFile)
                .forEach(File::delete);
    }

    @Test
    public void testSaveFile() throws IOException {
        int groupId = 1;
        String type = "documents";
        File tempFile = Files.createTempFile(tempDirectory, "testFile", ".txt").toFile();
        Files.write(tempFile.toPath(), "Test content".getBytes());

        fileService.saveFile(groupId, tempFile, type);

        String expectedPath = String.format("resources/%s/%d/%s", type, groupId, tempFile.getName());
        Path savedFilePath = Paths.get(fileService.getOutputFilePath());

        assertTrue(Files.exists(savedFilePath));
        assertEquals(expectedPath, fileService.getOutputFilePath());

        // Clean up
        Files.deleteIfExists(savedFilePath);
    }

    @Test
    public void testGetFileData() throws IOException {
        File tempFile = Files.createTempFile(tempDirectory, "testFile", ".txt").toFile();
        Files.write(tempFile.toPath(), "Test content".getBytes());

        FileData fileData = fileService.getFileData(tempFile.getPath());

        assertNotNull(fileData);
        assertEquals(tempFile.getName(), fileData.getFileName());
        assertEquals("bytes", fileData.getFileSize().split(" ")[1]);
        assertTrue(fileData.getFileType().startsWith("text/"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFileData_FileNotFound() {
        String invalidPath = "invalid/file/path.txt";
        fileService.getFileData(invalidPath);
    }
}
