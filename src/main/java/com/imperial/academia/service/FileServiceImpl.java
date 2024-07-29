package com.imperial.academia.service;

import com.imperial.academia.entity.chat_message.FileData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class FileServiceImpl implements FileService{
    private String outputFilePath = "";
    @Override
    public String getOutputFilePath() {
        return outputFilePath;
    }

    @Override
    public void saveFile(int groupId, File file, String type) {
        // Define the directory structure
        String directoryPath = String.format("resources/%s/%d/", type, groupId);
        directoryPath = directoryPath.replace("\\", "/");
        // Create the directory if it doesn't exist
        Path directory = Paths.get(directoryPath);
        try {
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            // Define the destination file path
            Path destination = directory.resolve(file.getName());

            // Copy the file to the destination
            Files.copy(file.toPath(), destination);
            outputFilePath = destination.toString();
            System.out.println("File saved to: " + destination.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save file");
        }
    }

    @Override
    public void saveFile(File file) {

    }

    @Override
    public void saveAvatar(int userId, File file) {
        // Define the directory structure
        String directoryPath = String.format("resources/avatars/%d/", userId);
        directoryPath = directoryPath.replace("\\", "/");
        // Create the directory if it doesn't exist
        Path directory = Paths.get(directoryPath);
        try {
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            // Define the destination file path
            Path destination = directory.resolve(file.getName());

            // If the file already exists, delete it
            if (Files.exists(destination)) {
                Files.delete(destination);
            }

            // Copy the file to the destination
            Files.copy(file.toPath(), destination);
            String outputFilePath = destination.toString();
            System.out.println("File saved to: " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save file");
        }
    }

    @Override
    public FileData getFileData(String filePath) {
        Path path = Paths.get(filePath);
        File file = path.toFile();

        if (!file.exists()) {
            throw new IllegalArgumentException("File not found: " + filePath);
        }

        String fileName = path.getFileName().toString();
        String fileSize = formatFileSize(file.length());
        String fileType = "";

        try {
            fileType = Files.probeContentType(path);
            if (fileType == null) {
                fileType = "Unknown";
            }
        } catch (IOException e) {
            fileType = "Unknown";
        }

        return new FileData(fileName, fileSize, fileType);
    }

    private String formatFileSize(long size) {
        String[] units = {"bytes", "KB", "MB", "GB", "TB"};
        int unitIndex = 0;
        double fileSize = size;

        while (fileSize >= 1024 && unitIndex < units.length - 1) {
            fileSize /= 1024;
            unitIndex++;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(fileSize) + " " + units[unitIndex];
    }


}
